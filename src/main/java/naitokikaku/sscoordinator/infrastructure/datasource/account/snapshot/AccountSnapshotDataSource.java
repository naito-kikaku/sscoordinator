package naitokikaku.sscoordinator.infrastructure.datasource.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import naitokikaku.sscoordinator.infrastructure.authentication.SSCoordinatorSecurityContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AccountSnapshotDataSource implements AccountSnapshotRepository {
    @Resource
    AccountSnapshotMapper mapper;
    @Resource
    SSCoordinatorSecurityContext context;

    @Override
    public void capture(AccountId accountId) {
        List<Long> transactionIds = mapper.findIncompleteCapturedTransactionIds(accountId);
        transactionIds.forEach(transactionId -> mapper.storeSnapshotByTransactionId(transactionId));
    }

    @Override
    public void capture() {
        List<Long> transactionIds = mapper.findIncompleteCapturedTransactionIds(context.accountId());
        transactionIds.forEach(transactionId -> mapper.storeSnapshotByTransactionId(transactionId));
    }

    @Override
    public AccountSnapshot findLatestBy(EmailAddress emailAddress) {
        return mapper.findLatestByEmailAddress(emailAddress);
    }

    @Override
    public AccountSnapshot getLatest() {
        return mapper.findLatestById(context.accountId());
    }
}
