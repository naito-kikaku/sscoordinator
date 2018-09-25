package naitokikaku.sscoordinator.infrastructure.datasource.account;

import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    AccountId nextAccountId();

    AccountId lock(@Param("accountId") AccountId accountId);

    boolean existsInActiveEmailAddresses(@Param("emailAddress") EmailAddress emailAddress);

    void storeTransaction(@Param("accountId") AccountId accountId, @Param("accountTransactionType") AccountTransactionType accountTransactionType);

    void store(@Param("accountId") AccountId accountId);

    void storeNameRevision(@Param("accountId") AccountId accountId,
                           @Param("accountName") AccountName accountName);

    void storeLatestNameRevision(@Param("accountId") AccountId accountId);

    void storeEmailAddressRevision(@Param("accountId") AccountId accountId,
                                   @Param("emailAddress") EmailAddress emailAddress);

    void storeLatestEmailAddressRevision(@Param("accountId") AccountId accountId);

    void storePasswordRevision(@Param("accountId") AccountId accountId,
                               @Param("encryptPassword") EncryptPassword encryptPassword);

    void storeLatestPasswordRevision(@Param("accountId") AccountId accountId);

    void storeDeletePointer(@Param("accountId") AccountId accountId);

    void storeActiveEmailAddress(@Param("emailAddress") EmailAddress emailAddress);

    void deleteLatestNamePointer(@Param("accountId") AccountId accountId);

    void deleteLatestEmailAddressPointer(@Param("accountId") AccountId accountId);

    void deleteLatestPasswordPointer(@Param("accountId") AccountId accountId);

    void deleteActiveEmailAddress(@Param("emailAddress") EmailAddress emailAddress);
}
