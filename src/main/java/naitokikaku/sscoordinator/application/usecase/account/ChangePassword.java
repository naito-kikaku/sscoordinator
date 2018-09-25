package naitokikaku.sscoordinator.application.usecase.account;

import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ChangePassword {
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;
    @Resource
    AccountRepository accountRepository;

    @Transactional
    public void execute(EncryptPassword encryptPassword) {
        accountRepository.lock();
        AccountSnapshot snapshot = accountSnapshotRepository.getLatest();
        if (encryptPassword.same(snapshot.password())) return;

        accountRepository.update(encryptPassword);
        accountSnapshotRepository.capture();
    }
}
