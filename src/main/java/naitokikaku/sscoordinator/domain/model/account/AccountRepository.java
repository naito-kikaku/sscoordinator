package naitokikaku.sscoordinator.domain.model.account;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountNameRevisionId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.revision.EmailAddressRevisionId;
import naitokikaku.sscoordinator.domain.model.account.revision.PasswordRevisionId;
import naitokikaku.sscoordinator.domain.model.fundamentals.email.EmailAddress;

public interface AccountRepository {

    AccountRevision store(Account account);

    AccountRevision update(AccountName accountName);

    AccountRevision update(EmailAddress emailAddress);

    AccountRevision update(EncryptPassword encryptPassword);

    AccountRevision delete(AccountId accountId);

    boolean alreadyUsed(EmailAddress emailAddress);
}
