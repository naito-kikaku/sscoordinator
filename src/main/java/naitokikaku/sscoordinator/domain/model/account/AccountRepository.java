package naitokikaku.sscoordinator.domain.model.account;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.fundamentals.email.EmailAddress;

public interface AccountRepository {

    void store(Account account, EncryptPassword encryptPassword);

    void update(Account account);

    void update(EncryptPassword encryptPassword);

    void delete(AccountId accountId);

    boolean alreadyUsed(EmailAddress emailAddress);

    void reserve(EmailAddress emailAddress);
}
