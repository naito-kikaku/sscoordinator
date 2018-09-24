package naitokikaku.sscoordinator.infrastructure.datasource.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import naitokikaku.sscoordinator.infrastructure.authentication.SSCoordinatorSecurityContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountSnapshotDataSource implements AccountSnapshotRepository {
    @Resource
    AccountSnapshotMapper mapper;
    @Resource
    SSCoordinatorSecurityContext context;

    @Override
    public void capture(AccountSnapshot accountSnapshot) {
        Long snapshotId = mapper.nextSnapshotId();
        mapper.store(snapshotId, accountSnapshot);
        mapper.storeAccountNameRevisionRelation(snapshotId, accountSnapshot.revision());
        mapper.storeEmailAddressRevisionRelation(snapshotId, accountSnapshot.revision());
        mapper.storePasswordRevisionRelation(snapshotId, accountSnapshot.revision());
    }

    @Override
    public AccountSnapshot findBy(EmailAddress emailAddress) {
        return mapper.findLatestByEmailAddress(emailAddress);
    }

    @Override
    public AccountSnapshot get() {
        return mapper.findLatestById(context.accountId());
    }
}
