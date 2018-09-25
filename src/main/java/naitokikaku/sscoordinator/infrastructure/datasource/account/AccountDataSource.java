package naitokikaku.sscoordinator.infrastructure.datasource.account;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
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
    public void store(Account account) {
        AccountId accountId = mapper.nextAccountId();
        mapper.store(accountId);
        mapper.storeNameRevision(accountId, account.name());
        mapper.storeLatestNameRevision(accountId);
        mapper.storeEmailAddressRevision(accountId, account.emailAddress());
        mapper.storeLatestEmailAddressRevision(accountId);
        mapper.storePasswordRevision(accountId, account.password());
        mapper.storeLatestPasswordRevision(accountId);
        mapper.storeTransaction(accountId, AccountTransactionType.SIGN_UP);
    }

    @Override
    public void lock() {
        mapper.lock(context.accountId());
    }

    @Override
    public void update(AccountName accountName) {
        mapper.storeNameRevision(context.accountId(), accountName);
        mapper.deleteLatestNamePointer(context.accountId());
        mapper.storeLatestNameRevision(context.accountId());
        mapper.storeTransaction(context.accountId(), AccountTransactionType.CHANGE_ACCOUNT_NAME);
    }

    @Override
    public void update(EmailAddress emailAddress) {
        mapper.storeEmailAddressRevision(context.accountId(), emailAddress);
        mapper.deleteLatestEmailAddressPointer(context.accountId());
        mapper.storeLatestEmailAddressRevision(context.accountId());
        mapper.storeTransaction(context.accountId(), AccountTransactionType.CHANGE_EMAIL_ADDRESS);
    }

    @Override
    public void update(EncryptPassword encryptPassword) {
        mapper.storePasswordRevision(context.accountId(), encryptPassword);
        mapper.deleteLatestPasswordPointer(context.accountId());
        mapper.storeLatestPasswordRevision(context.accountId());
        mapper.storeTransaction(context.accountId(), AccountTransactionType.CHANGE_PASSWORD);
    }

    @Override
    public void delete() {
        mapper.storeDeletePointer(context.accountId());
        mapper.storeTransaction(context.accountId(), AccountTransactionType.DELETE);
    }

    @Override
    public void storeActive(EmailAddress emailAddress) {
        mapper.storeActiveEmailAddress(emailAddress);
    }

    @Override
    public void deleteActive(EmailAddress emailAddress) {
        mapper.deleteActiveEmailAddress(emailAddress);
    }

}
