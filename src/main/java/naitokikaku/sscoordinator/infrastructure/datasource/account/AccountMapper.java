package naitokikaku.sscoordinator.infrastructure.datasource.account;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.fundamentals.email.EmailAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    boolean existsInActiveEmailAddresses(@Param("emailAddress") EmailAddress emailAddress);

    AccountId nextAccountId();

    void store(@Param("accountId") AccountId accountId);

    Long nextAccountNameRevisionId();

    void storeNameRevision(@Param("accountId") AccountId accountId,
                           @Param("accountNameRevisionId") Long accountNameRevisionId,
                           @Param("account") Account account);

    void storeLatestNameRevision(@Param("accountId") AccountId accountId,
                                 @Param("accountNameRevisionId") Long accountNameRevisionId);

    Long nextEmailAddressRevisionId();

    void storeEmailAddressRevision(@Param("accountId") AccountId accountId,
                                   @Param("emailAddressRevisionId") Long emailAddressRevisionId,
                                   @Param("account") Account account);

    void storeLatestEmailAddressRevision(@Param("accountId") AccountId accountId,
                                         @Param("emailAddressRevisionId") Long emailAddressRevisionId);

    Long nextPasswordRevisionId();

    void storePasswordRevision(@Param("accountId") AccountId accountId,
                               @Param("passwordRevisionId") Long passwordRevisionId,
                               @Param("encryptPassword") EncryptPassword encryptPassword);

    void storeLatestPasswordRevision(@Param("accountId") AccountId accountId,
                                     @Param("passwordRevisionId") Long passwordRevisionId);

    void storeActiveEmailAddress(@Param("account") Account account);
}
