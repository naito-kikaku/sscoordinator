package naitokikaku.sscoordinator.application.usecase.account.changename.complete;

import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotFactory;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import naitokikaku.sscoordinator.domain.model.account.status.AccountStatus;
import naitokikaku.sscoordinator.domain.model.account.status.EnableClass;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class CaptureChangeAccountNameAccountSnapshot {
    @Resource
    AccountSnapshotFactory accountSnapshotFactory;
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;

    @EventListener
    @Transactional
    public void execute(ChangeAccountNameCompleteEvent event) {
        AccountSnapshot snapshot = accountSnapshotFactory.create(event.account, new AccountStatus(EnableClass.ENABLE), event.revision);
        accountSnapshotRepository.capture(snapshot);
    }
}
