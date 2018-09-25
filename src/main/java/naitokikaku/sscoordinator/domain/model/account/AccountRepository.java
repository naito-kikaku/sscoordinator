package naitokikaku.sscoordinator.domain.model.account;

import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;

public interface AccountRepository {

    boolean alreadyUsed(EmailAddress emailAddress);

    void store(Account account);

    void lock();

    void update(AccountName accountName);

    void update(EmailAddress emailAddress);

    void update(EncryptPassword encryptPassword);

    void delete();

    void storeActive(EmailAddress emailAddress);

    void deleteActive(EmailAddress emailAddress);
}
