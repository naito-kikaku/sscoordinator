package naitokikaku.sscoordinator.presentation.controller.signup;

import naitokikaku.sscoordinator.application.usecase.account.signup.SignUp;
import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountFactory;
import naitokikaku.sscoordinator.domain.model.account.password.policy.PasswordPolicy;
import naitokikaku.sscoordinator.domain.model.account.password.policy.PasswordPolicyViolation;
import naitokikaku.sscoordinator.domain.model.account.policy.AccountPolicy;
import naitokikaku.sscoordinator.domain.model.account.policy.AccountPolicyViolations;
import naitokikaku.sscoordinator.infrastructure.authentication.InnerAuthenticationService;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
@SessionAttributes({"signUpForm"})
public class SignUpFormController {

    @ModelAttribute("pageInfo")
    public PageInfo pageInfo() {
        return new SignUpPageInfo();
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signup";
    }

    @Resource
    @Qualifier("noConstraint")
    PasswordPolicy passwordPolicy;
    @Resource
    AccountPolicy accountPolicy;
    @Resource
    AccountFactory accountFactory;
    @Resource
    SignUp signUp;
    @Resource
    InnerAuthenticationService innerAuthenticationService;

    @PostMapping
    public String post(@Valid @ModelAttribute("signUpForm") SignUpForm signUpForm, BindingResult binding,
                       Model model, SessionStatus status) {
        if (binding.hasErrors()) return "signup";
        PasswordPolicyViolation passwordPolicyViolation = passwordPolicy.valid(signUpForm.password);
        if (!passwordPolicyViolation.isNothing()) {
            model.addAttribute("passwordPolicyViolation", passwordPolicyViolation);
            return "signup";
        }
        Account account = accountFactory.create(signUpForm.accountName, signUpForm.emailAddress, signUpForm.password);
        AccountPolicyViolations accountPolicyViolations = accountPolicy.valid(account);
        if (!accountPolicyViolations.isNothing()) {
            model.addAttribute("accountPolicyViolations", accountPolicyViolations);
            return "signup";
        }
        signUp.execute(account);
        innerAuthenticationService.authenticate(signUpForm.emailAddress, signUpForm.password);
        status.setComplete();
        return "redirect:/home";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(allowFields);
    }

    private static String[] allowFields = new String[]{
            "accountName.value",
            "emailAddress.value",
            "password.value",
            "retypedPassword.value"
    };
}
