package naitokikaku.sscoordinator.domain.model.account.policy;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NotDuplicateEmailAddress {
    @Resource
    AccountRepository accountRepository;

    public boolean ok(Account account) {
        return !accountRepository.alreadyUsed(account.emailAddress());
    }

    public AccountPolicyViolation valid(Account account) {
        boolean duplicatedEmail = accountRepository.alreadyUsed(account.emailAddress());
        if (duplicatedEmail) return new AccountPolicyViolation("すでに登録されているメールアドレスです");
        return new AccountPolicyViolation();
    }
}
