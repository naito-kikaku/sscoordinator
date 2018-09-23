package naitokikaku.sscoordinator.domain.model.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;

public interface AccountSnapshotRepository {

    void capture(AccountSnapshot accountSnapshot);

    AccountSnapshot get(AccountId accountId);

    AccountSnapshot findBy(EmailAddress emailAddress);
}
