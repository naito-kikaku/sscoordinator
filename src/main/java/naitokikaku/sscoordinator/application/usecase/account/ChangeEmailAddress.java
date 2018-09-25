package naitokikaku.sscoordinator.application.usecase.account;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.policy.AccountPolicy;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ChangeEmailAddress {
    @Resource
    AccountPolicy accountPolicy;
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;
    @Resource
    AccountRepository accountRepository;

    @Transactional
    public void execute(EmailAddress emailAddress) {
        AccountSnapshot snapshot = accountSnapshotRepository.getLatest();
        Account draft = snapshot.account().replace(emailAddress);
        if (!accountPolicy.ok(draft)) throw new IllegalArgumentException();
        if (emailAddress.same(snapshot.emailAddress())) return;

        accountRepository.deleteActive(snapshot.emailAddress());
        accountRepository.storeActive(emailAddress);
        accountRepository.update(emailAddress);
        accountSnapshotRepository.capture();
    }
}
