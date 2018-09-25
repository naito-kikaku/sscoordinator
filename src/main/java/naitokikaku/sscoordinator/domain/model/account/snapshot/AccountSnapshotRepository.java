package naitokikaku.sscoordinator.domain.model.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;

public interface AccountSnapshotRepository {

    void capture(AccountId accountId);

    void capture();

    AccountSnapshot findLatestBy(EmailAddress emailAddress);

    AccountSnapshot getLatest();
}
