package naitokikaku.sscoordinator.domain.model.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.status.AccountStatus;
import org.springframework.stereotype.Component;

@Component
public class AccountSnapshotFactory {

    public AccountSnapshot create(Account account, AccountStatus status, AccountRevision revision) {
        return new AccountSnapshot(account, status, revision);
    }
}
