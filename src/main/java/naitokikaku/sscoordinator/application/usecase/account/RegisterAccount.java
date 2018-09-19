package naitokikaku.sscoordinator.application.usecase.account;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.policy.AccountPolicy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class RegisterAccount {
    @Resource
    AccountPolicy accountPolicy;
    @Resource
    AccountRepository accountRepository;

    @Transactional
    public void execute(Account account, EncryptPassword encryptPassword) {
        if (!accountPolicy.ok(account)) throw new IllegalArgumentException();
        accountRepository.reserve(account.emailAddress());
        accountRepository.store(account, encryptPassword);
    }
}
