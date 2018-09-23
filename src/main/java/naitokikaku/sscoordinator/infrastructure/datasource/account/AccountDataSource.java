package naitokikaku.sscoordinator.infrastructure.datasource.account;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.revision.*;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.CreatedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.RevisedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.email.EmailAddress;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountDataSource implements AccountRepository {
    @Resource
    AccountMapper mapper;

    @Override
    public boolean alreadyUsed(EmailAddress emailAddress) {
        return mapper.existsInActiveEmailAddresses(emailAddress);
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

        mapper.storeActiveEmailAddress(account.emailAddress());

        RevisedDateTime revisedDateTime = mapper.getPasswordRevisionRevisedDateTime(passwordRevisionId);
        CreatedDateTime createdDateTime = mapper.getAccountCreatedDateTime(accountId);
        return new AccountRevision(accountId, accountNameRevisionId, emailAddressRevisionId, passwordRevisionId, new DeletedDateTime(), revisedDateTime, createdDateTime);
    }

    @Override
    public AccountRevision update(AccountName accountName) {
        // TODO impl
        return null;
    }

    @Override
    public AccountRevision update(EmailAddress emailAddress) {
        // TODO impl
        return null;
    }

    @Override
    public AccountRevision update(EncryptPassword encryptPassword) {
        // TODO impl
        return null;
    }

    @Override
    public AccountRevision delete(AccountId accountId) {
        // TODO impl
        return null;
    }

}
