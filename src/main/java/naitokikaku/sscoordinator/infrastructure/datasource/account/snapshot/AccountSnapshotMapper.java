package naitokikaku.sscoordinator.infrastructure.datasource.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountSnapshotMapper {

    void storeSnapshotByTransactionId(@Param("accountTransactionId") Long accountTransactionId);

    AccountSnapshot findLatestById(@Param("accountId") AccountId accountId);

    AccountSnapshot findLatestByEmailAddress(@Param("emailAddress") EmailAddress emailAddress);

    List<Long> findIncompleteCapturedTransactionIds(@Param("accountId") AccountId accountId);

}
