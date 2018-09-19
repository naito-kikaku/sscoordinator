package naitokikaku.sscoordinator.domain.model.account;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.fundamentals.email.EmailAddress;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class Account implements Serializable {
    AccountId id = new AccountId();
    AccountName name = new AccountName();
    EmailAddress emailAddress = new EmailAddress();

    Account() {
    }

    Account(AccountName name, EmailAddress emailAddress) {
        this(new AccountId(), name, emailAddress);
    }

    Account(AccountId id, AccountName name, EmailAddress emailAddress) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public EmailAddress emailAddress() {
        return emailAddress;
    }
}
