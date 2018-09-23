package naitokikaku.sscoordinator.domain.model.account;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class Account implements Serializable {
    AccountId id = new AccountId();
    AccountName name = new AccountName();
    EmailAddress emailAddress = new EmailAddress();
    EncryptPassword password = new EncryptPassword();

    public Account() {
    }

    Account(AccountName name, EmailAddress emailAddress, EncryptPassword password) {
        this(new AccountId(), name, emailAddress, password);
    }

    Account(AccountId id, AccountName name, EmailAddress emailAddress, EncryptPassword password) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public AccountId id() {
        return id;
    }

    public EmailAddress emailAddress() {
        return emailAddress;
    }

    public AccountName name() {
        return name;
    }

    public EncryptPassword password() {
        return password;
    }

    public void fix(AccountId accountId) {
        this.id = accountId;
    }
}
