package naitokikaku.sscoordinator.application.usecase.account.changename;

import naitokikaku.sscoordinator.application.usecase.account.changename.complete.ChangeAccountNameCompleteEvent;
import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ChangeAccountName {
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;
    @Resource
    AccountRepository accountRepository;

    @Resource
    ApplicationEventPublisher publisher;

    @Transactional
    public void execute(AccountName accountName) {
        AccountSnapshot snapshot = accountSnapshotRepository.get();
        if (accountName.same(snapshot.accountName())) return;

        AccountRevision updatedRevision = accountRepository.update(accountName);

        Account updatedAccount = snapshot.account().replace(accountName);
        publisher.publishEvent(new ChangeAccountNameCompleteEvent(this, updatedAccount, updatedRevision));
    }
}
