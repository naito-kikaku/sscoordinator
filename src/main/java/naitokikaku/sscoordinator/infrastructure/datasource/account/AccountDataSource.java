package naitokikaku.sscoordinator.infrastructure.datasource.account;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
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
    public void reserve(EmailAddress emailAddress) {

    }

    @Override
    public void store(Account account, EncryptPassword encryptPassword) {
        AccountId accountId = mapper.nextAccountId();
        mapper.store(accountId);
        Long accountNameRevisionId = mapper.nextAccountNameRevisionId();
        mapper.storeNameRevision(accountId, accountNameRevisionId, account);
        mapper.storeLatestNameRevision(accountId, accountNameRevisionId);
        Long emailAddressRevisionId = mapper.nextEmailAddressRevisionId();
        mapper.storeEmailAddressRevision(accountId, emailAddressRevisionId, account);
        mapper.storeLatestEmailAddressRevision(accountId, emailAddressRevisionId);
        Long passwordRevisionId = mapper.nextPasswordRevisionId();
        mapper.storePasswordRevision(accountId, passwordRevisionId, encryptPassword);
        mapper.storeLatestPasswordRevision(accountId, passwordRevisionId);
        mapper.storeActiveEmailAddress(account);
    }

    @Override
    public void update(Account account) {
        // TODO impl
    }

    @Override
    public void update(EncryptPassword encryptPassword) {
        // TODO impl
    }

    @Override
    public void delete(AccountId accountId) {
        // TODO impl
    }

}
