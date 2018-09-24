package naitokikaku.sscoordinator.application.usecase.account.changepassword;

import naitokikaku.sscoordinator.application.usecase.account.changepassword.complete.ChangePasswordCompleteEvent;
import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ChangePassword {
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;
    @Resource
    AccountRepository accountRepository;
    @Resource
    ApplicationEventPublisher publisher;

    @Transactional
    public void execute(EncryptPassword encryptPassword) {
        AccountSnapshot snapshot = accountSnapshotRepository.get();
        if (encryptPassword.same(snapshot.password())) return;

        AccountRevision updatedRevision = accountRepository.update(encryptPassword);

        Account updatedAccount = snapshot.account().replace(encryptPassword);
        publisher.publishEvent(new ChangePasswordCompleteEvent(this, updatedAccount, updatedRevision));
    }
}
