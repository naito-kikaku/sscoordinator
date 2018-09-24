package naitokikaku.sscoordinator.application.usecase.account.delete;

import naitokikaku.sscoordinator.application.usecase.account.delete.complete.DeleteAccountEvent;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class DeleteAccount {
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;
    @Resource
    AccountRepository accountRepository;
    @Resource
    ApplicationEventPublisher publisher;

    @Transactional
    public void execute() {
        AccountSnapshot snapshot = accountSnapshotRepository.get();

        AccountRevision deletedRevision = accountRepository.delete();
        accountRepository.deleteActive(snapshot.emailAddress());

        publisher.publishEvent(new DeleteAccountEvent(this, snapshot.account(), deletedRevision));
    }
}
