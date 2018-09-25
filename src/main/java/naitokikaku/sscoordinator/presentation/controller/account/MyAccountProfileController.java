package naitokikaku.sscoordinator.presentation.controller.account;

import naitokikaku.sscoordinator.application.usecase.account.ChangeEmailAddress;
import naitokikaku.sscoordinator.application.usecase.account.ChangeAccountName;
import naitokikaku.sscoordinator.application.usecase.account.ChangePassword;
import naitokikaku.sscoordinator.application.usecase.account.DeleteAccount;
import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.password.policy.PasswordPolicy;
import naitokikaku.sscoordinator.domain.model.account.password.policy.PasswordPolicyViolation;
import naitokikaku.sscoordinator.domain.model.account.policy.AccountPolicy;
import naitokikaku.sscoordinator.domain.model.account.policy.AccountPolicyViolations;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.Breadcrumb;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import naitokikaku.sscoordinator.presentation.controller.home.HomePageInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/myaccount/profile")
@SessionAttributes({"accountName", "emailAddress", "passwordForm"})
public class MyAccountProfileController {

    @ModelAttribute("pageInfo")
    public PageInfo pageInfo() {
        return new MyAccountProfilePageInfo();
    }

    @ModelAttribute("breadcrumb")
    public Breadcrumb breadcrumb() {
        return new Breadcrumb(Arrays.asList(
                new HomePageInfo(),
                new MyAccountProfilePageInfo()
        ));
    }

    @Resource
    AccountSnapshotRepository accountSnapshotRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("accountName", new AccountName());
        model.addAttribute("emailAddress", new EmailAddress());
        model.addAttribute("passwordForm", new PasswordForm());
        return "account/profile";
    }

    @Resource
    ChangeAccountName changeAccountName;

    @PostMapping(params = "accountName")
    public String postAccountName(@Validated @ModelAttribute("accountName") AccountName accountName, BindingResult binding,
                                  RedirectAttributes attributes, SessionStatus status) {
        if (binding.hasErrors()) {
            attributes.addFlashAttribute("bindingErrors", binding.getFieldErrors());
            return "redirect:/myaccount/profile";
        }
        AccountSnapshot snapshot = accountSnapshotRepository.getLatest();
        if (accountName.same(snapshot.accountName())) return "redirect:/myaccount/profile";
        changeAccountName.execute(accountName);
        attributes.addFlashAttribute("successMessage", "アカウント名を変更しました。");
        status.setComplete();
        return "redirect:/myaccount/profile";
    }

    @Resource
    AccountPolicy accountPolicy;
    @Resource
    ChangeEmailAddress changeEmailAddress;

    @PostMapping(params = "emailAddress")
    public String postEmailAddress(@Validated @ModelAttribute("emailAddress") EmailAddress emailAddress, BindingResult binding,
                                   RedirectAttributes attributes, SessionStatus status) {
        if (binding.hasErrors()) {
            attributes.addFlashAttribute("bindingErrors", binding.getAllErrors());
            return "redirect:/myaccount/profile";
        }
        AccountSnapshot snapshot = accountSnapshotRepository.getLatest();
        if (emailAddress.same(snapshot.emailAddress())) return "redirect:/myaccount/profile";
        Account draft = snapshot.account().replace(emailAddress);
        AccountPolicyViolations accountPolicyViolations = accountPolicy.valid(draft);
        if (!accountPolicyViolations.isNothing()) {
            attributes.addFlashAttribute("accountPolicyViolations", accountPolicyViolations);
            return "redirect:/myaccount/profile";
        }
        changeEmailAddress.execute(emailAddress);
        attributes.addFlashAttribute("successMessage", "メールアドレスを変更しました。");
        status.setComplete();
        return "redirect:/myaccount/profile";
    }

    @Resource
    @Qualifier("noConstraint")
    PasswordPolicy passwordPolicy;
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    ChangePassword changePassword;

    @PostMapping(params = "password")
    public String postPassword(@Validated @ModelAttribute("passwordForm") PasswordForm passwordForm, BindingResult binding,
                               RedirectAttributes attributes, SessionStatus status) {
        AccountSnapshot snapshot = accountSnapshotRepository.getLatest();
        boolean currentPasswordUnmatch = !passwordEncoder.matches(passwordForm.currentPassword.toString(), snapshot.password().toString());
        if (currentPasswordUnmatch) {
            attributes.addFlashAttribute("errorMessage", "現在のパスワードが間違っています。");
            return "redirect:/myaccount/profile";
        }
        if (binding.hasErrors()) {
            attributes.addFlashAttribute("bindingErrors", binding.getAllErrors());
            return "redirect:/myaccount/profile";
        }
        PasswordPolicyViolation passwordPolicyViolation = passwordPolicy.valid(passwordForm.newPassword);
        if (!passwordPolicyViolation.isNothing()) {
            attributes.addFlashAttribute("passwordPolicyViolation", passwordPolicyViolation);
            return "redirect:/myaccount/profile";
        }
        String encryptPassword = passwordEncoder.encode(passwordForm.newPassword.toString());
        changePassword.execute(new EncryptPassword(encryptPassword));
        attributes.addFlashAttribute("successMessage", "パスワードを変更しました。");
        status.setComplete();
        return "redirect:/myaccount/profile";
    }

    @Resource
    DeleteAccount deleteAccount;

    @PostMapping(params = "delete")
    public String postPassword(SessionStatus status) {
        deleteAccount.execute();
        status.setComplete();
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(allowFields);
    }

    private static String[] allowFields = new String[]{
            "value",
            "currentPassword.value",
            "newPassword.value",
            "retypedNewPassword.value"
    };
}
