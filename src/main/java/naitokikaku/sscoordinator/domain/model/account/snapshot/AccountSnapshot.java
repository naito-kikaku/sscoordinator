package naitokikaku.sscoordinator.domain.model.account.snapshot;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountName;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.status.AccountStatus;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class AccountSnapshot implements Serializable {
    Account account = new Account();
    AccountStatus accountStatus = new AccountStatus();
    AccountRevision revision = new AccountRevision();

    public AccountSnapshot() {
    }

    public AccountSnapshot(Account account, AccountStatus accountStatus, AccountRevision revision) {
        this.account = account;
        this.accountStatus = accountStatus;
        this.revision = revision;
    }

    public AccountName accountName() {
        return account.name();
    }

    public EmailAddress emailAddress() {
        return account.emailAddress();
    }

    public EncryptPassword password() {
        return account.password();
    }

    public Account account() {
        return account;
    }

    public AccountRevision revision() {
        return revision;
    }

    public AccountStatus accountStatus() {
        return accountStatus;
    }


}
