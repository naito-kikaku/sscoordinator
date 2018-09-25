package naitokikaku.sscoordinator.application.usecase.account;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.policy.AccountPolicy;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class SignUp {
    @Resource
    AccountPolicy accountPolicy;
    @Resource
    AccountRepository accountRepository;
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;

    @Transactional
    public void execute(Account account) {
        if (!accountPolicy.ok(account)) throw new IllegalArgumentException();

        accountRepository.storeActive(account.emailAddress());
        accountRepository.store(account);
        accountSnapshotRepository.capture(account.id());
    }
}
