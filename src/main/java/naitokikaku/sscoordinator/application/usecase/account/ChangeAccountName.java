package naitokikaku.sscoordinator.application.usecase.account;

import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ChangeAccountName {
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;
    @Resource
    AccountRepository accountRepository;

    @Transactional
    public void execute(AccountName accountName) {
        accountRepository.lock();
        AccountSnapshot snapshot = accountSnapshotRepository.getLatest();
        if (accountName.same(snapshot.accountName())) return;

        accountRepository.update(accountName);
        accountSnapshotRepository.capture();
    }
}
