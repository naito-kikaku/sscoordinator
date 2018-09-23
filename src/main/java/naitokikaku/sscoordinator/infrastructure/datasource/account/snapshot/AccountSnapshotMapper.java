package naitokikaku.sscoordinator.infrastructure.datasource.account.snapshot;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountSnapshotMapper {

    Long nextSnapshotId();

    void store(@Param("accountSnapshotId") Long accountSnapshotId,
               @Param("accountSnapshot") AccountSnapshot accountSnapshot);

    void storeAccountNameRevisionRelation(@Param("accountSnapshotId") Long snapshotId,
                                          @Param("accountRevision") AccountRevision accountRevision);

    void storeEmailAddressRevisionRelation(@Param("accountSnapshotId") Long snapshotId,
                                           @Param("accountRevision") AccountRevision accountRevision);

    void storePasswordRevisionRelation(@Param("accountSnapshotId") Long snapshotId,
                                       @Param("accountRevision") AccountRevision accountRevision);

    AccountSnapshot findLatestById(@Param("accountId") AccountId accountId);
}
