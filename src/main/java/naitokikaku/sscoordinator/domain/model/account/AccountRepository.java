package naitokikaku.sscoordinator.domain.model.account;

import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;

public interface AccountRepository {

    AccountRevision store(Account account);

    AccountRevision update(AccountName accountName);

    AccountRevision update(EmailAddress emailAddress);

    AccountRevision update(EncryptPassword encryptPassword);

    AccountRevision delete();

    boolean alreadyUsed(EmailAddress emailAddress);

    void storeActive(EmailAddress emailAddress);

    void deleteActive(EmailAddress emailAddress);
}
