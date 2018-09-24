package naitokikaku.sscoordinator.infrastructure.datasource.account;

import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountNameRevisionId;
import naitokikaku.sscoordinator.domain.model.account.revision.DeletedDateTime;
import naitokikaku.sscoordinator.domain.model.account.revision.EmailAddressRevisionId;
import naitokikaku.sscoordinator.domain.model.account.revision.PasswordRevisionId;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.CreatedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.RevisedDateTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    boolean existsInActiveEmailAddresses(@Param("emailAddress") EmailAddress emailAddress);

    AccountId nextAccountId();

    void store(@Param("accountId") AccountId accountId);

    AccountNameRevisionId nextAccountNameRevisionId();

    void storeNameRevision(@Param("accountId") AccountId accountId,
                           @Param("accountNameRevisionId") AccountNameRevisionId accountNameRevisionId,
                           @Param("accountName") AccountName accountName);

    void storeLatestNameRevision(@Param("accountId") AccountId accountId,
                                 @Param("accountNameRevisionId") AccountNameRevisionId accountNameRevisionId);

    EmailAddressRevisionId nextEmailAddressRevisionId();

    void storeEmailAddressRevision(@Param("accountId") AccountId accountId,
                                   @Param("emailAddressRevisionId") EmailAddressRevisionId emailAddressRevisionId,
                                   @Param("emailAddress") EmailAddress emailAddress);

    void storeLatestEmailAddressRevision(@Param("accountId") AccountId accountId,
                                         @Param("emailAddressRevisionId") EmailAddressRevisionId emailAddressRevisionId);

    PasswordRevisionId nextPasswordRevisionId();

    void storePasswordRevision(@Param("accountId") AccountId accountId,
                               @Param("passwordRevisionId") PasswordRevisionId passwordRevisionId,
                               @Param("encryptPassword") EncryptPassword encryptPassword);

    void storeLatestPasswordRevision(@Param("accountId") AccountId accountId,
                                     @Param("passwordRevisionId") PasswordRevisionId passwordRevisionId);

    void storeActiveEmailAddress(@Param("emailAddress") EmailAddress emailAddress);

    CreatedDateTime getAccountCreatedDateTime(@Param("accountId") AccountId accountId);

    RevisedDateTime getNameRevisionRevisedDateTime(@Param("accountNameRevisionId") AccountNameRevisionId accountNameRevisionId);

    RevisedDateTime getEmailAddressRevisionRevisedDateTime(@Param("emailAddressRevisionId") EmailAddressRevisionId emailAddressRevisionId);

    RevisedDateTime getPasswordRevisionRevisedDateTime(@Param("passwordRevisionId") PasswordRevisionId passwordRevisionId);

    DeletedDateTime getAccountDeletedDateTime(@Param("accountId") AccountId accountId);

    AccountNameRevisionId getLatestNameRevisionId(@Param("accountId") AccountId accountId);

    EmailAddressRevisionId getLatestEmailAddressRevisionId(@Param("accountId") AccountId accountId);

    PasswordRevisionId getLatestPasswordRevisionId(@Param("accountId") AccountId accountId);

    void deleteLatestNamePointer(@Param("accountId") AccountId accountId);

    void deleteLatestEmailAddressPointer(@Param("accountId") AccountId accountId);

    void deleteLatestPasswordPointer(@Param("accountId") AccountId accountId);

    void deleteActiveEmailAddress(@Param("emailAddress") EmailAddress emailAddress);
}
