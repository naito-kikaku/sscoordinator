package naitokikaku.sscoordinator.domain.model.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.EmailAddress;

public interface AccountSnapshotRepository {

    void capture(AccountSnapshot accountSnapshot);

    AccountSnapshot findBy(EmailAddress emailAddress);

    AccountSnapshot get();
}
