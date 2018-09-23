package naitokikaku.sscoordinator.domain.model.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;

public interface AccountSnapshotRepository {

    void capture(AccountSnapshot accountSnapshot);

    AccountSnapshot get(AccountId accountId);
}
