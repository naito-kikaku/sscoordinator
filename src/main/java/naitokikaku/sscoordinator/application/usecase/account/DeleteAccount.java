package naitokikaku.sscoordinator.application.usecase.account;

import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class DeleteAccount {
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;
    @Resource
    AccountRepository accountRepository;

    @Transactional
    public void execute() {
        accountRepository.lock();
        AccountSnapshot snapshot = accountSnapshotRepository.getLatest();

        accountRepository.deleteActive(snapshot.emailAddress());
        accountRepository.delete();
        accountSnapshotRepository.capture();
    }
}
