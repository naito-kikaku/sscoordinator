package naitokikaku.sscoordinator.infrastructure.datasource.account;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.revision.*;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.CreatedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.RevisedDateTime;
import naitokikaku.sscoordinator.infrastructure.authentication.SSCoordinatorSecurityContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountDataSource implements AccountRepository {
    @Resource
    AccountMapper mapper;
    @Resource
    SSCoordinatorSecurityContext context;

    @Override
    public boolean alreadyUsed(EmailAddress emailAddress) {
        return mapper.existsInActiveEmailAddresses(emailAddress);
    }

    @Override
    public void storeActive(EmailAddress emailAddress) {
        mapper.storeActiveEmailAddress(emailAddress);
    }

    @Override
    public void deleteActive(EmailAddress emailAddress) {
        mapper.deleteActiveEmailAddress(emailAddress);
    }

    @Override
    public AccountRevision store(Account account) {
        AccountId accountId = mapper.nextAccountId();
        mapper.store(accountId);
        account.fix(accountId);
        AccountNameRevisionId accountNameRevisionId = mapper.nextAccountNameRevisionId();
        mapper.storeNameRevision(accountId, accountNameRevisionId, account.name());
        mapper.storeLatestNameRevision(accountId, accountNameRevisionId);
        EmailAddressRevisionId emailAddressRevisionId = mapper.nextEmailAddressRevisionId();
        mapper.storeEmailAddressRevision(accountId, emailAddressRevisionId, account.emailAddress());
        mapper.storeLatestEmailAddressRevision(accountId, emailAddressRevisionId);
        PasswordRevisionId passwordRevisionId = mapper.nextPasswordRevisionId();
        mapper.storePasswordRevision(accountId, passwordRevisionId, account.password());
        mapper.storeLatestPasswordRevision(accountId, passwordRevisionId);

        RevisedDateTime revisedDateTime = mapper.getPasswordRevisionRevisedDateTime(passwordRevisionId);
        CreatedDateTime createdDateTime = mapper.getAccountCreatedDateTime(accountId);
        return new AccountRevision(accountId, accountNameRevisionId, emailAddressRevisionId, passwordRevisionId, new DeletedDateTime(), revisedDateTime, createdDateTime);
    }

    @Override
    public AccountRevision update(AccountName accountName) {
        AccountNameRevisionId accountNameRevisionId = mapper.nextAccountNameRevisionId();
        mapper.storeNameRevision(context.accountId(), accountNameRevisionId, accountName);
        mapper.deleteLatestNamePointer(context.accountId());
        mapper.storeLatestNameRevision(context.accountId(), accountNameRevisionId);

        EmailAddressRevisionId latestEmailAddressRevisionId = mapper.getLatestEmailAddressRevisionId(context.accountId());
        PasswordRevisionId latestPasswordRevisionId = mapper.getLatestPasswordRevisionId(context.accountId());
        RevisedDateTime revisedDateTime = mapper.getNameRevisionRevisedDateTime(accountNameRevisionId);
        CreatedDateTime createdDateTime = mapper.getAccountCreatedDateTime(context.accountId());
        return new AccountRevision(context.accountId(), accountNameRevisionId, latestEmailAddressRevisionId, latestPasswordRevisionId, new DeletedDateTime(), revisedDateTime, createdDateTime);
    }

    @Override
    public AccountRevision update(EmailAddress emailAddress) {
        EmailAddressRevisionId emailAddressRevisionId = mapper.nextEmailAddressRevisionId();
        mapper.storeEmailAddressRevision(context.accountId(), emailAddressRevisionId, emailAddress);
        mapper.deleteLatestEmailAddressPointer(context.accountId());
        mapper.storeLatestEmailAddressRevision(context.accountId(), emailAddressRevisionId);

        AccountNameRevisionId latestNameRevisionId = mapper.getLatestNameRevisionId(context.accountId());
        PasswordRevisionId latestPasswordRevisionId = mapper.getLatestPasswordRevisionId(context.accountId());
        RevisedDateTime revisedDateTime = mapper.getEmailAddressRevisionRevisedDateTime(emailAddressRevisionId);
        CreatedDateTime createdDateTime = mapper.getAccountCreatedDateTime(context.accountId());
        return new AccountRevision(context.accountId(), latestNameRevisionId, emailAddressRevisionId, latestPasswordRevisionId, new DeletedDateTime(), revisedDateTime, createdDateTime);
    }

    @Override
    public AccountRevision update(EncryptPassword encryptPassword) {
        PasswordRevisionId passwordRevisionId = mapper.nextPasswordRevisionId();
        mapper.storePasswordRevision(context.accountId(), passwordRevisionId, encryptPassword);
        mapper.deleteLatestPasswordPointer(context.accountId());
        mapper.storeLatestPasswordRevision(context.accountId(), passwordRevisionId);

        AccountNameRevisionId latestNameRevisionId = mapper.getLatestNameRevisionId(context.accountId());
        EmailAddressRevisionId latestEmailAddressRevisionId = mapper.getLatestEmailAddressRevisionId(context.accountId());
        RevisedDateTime revisedDateTime = mapper.getPasswordRevisionRevisedDateTime(passwordRevisionId);
        CreatedDateTime createdDateTime = mapper.getAccountCreatedDateTime(context.accountId());
        return new AccountRevision(context.accountId(), latestNameRevisionId, latestEmailAddressRevisionId, passwordRevisionId, new DeletedDateTime(), revisedDateTime, createdDateTime);
    }

    @Override
    public AccountRevision delete(AccountId accountId) {
        // TODO impl
        return null;
    }

}
