package naitokikaku.sscoordinator.domain.model.account.revision;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.CreatedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.RevisedDateTime;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class AccountRevision implements Serializable {
    AccountId accountId = new AccountId();
    AccountNameRevisionId accountNameRevisionId = new AccountNameRevisionId();
    EmailAddressRevisionId emailAddressRevisionId = new EmailAddressRevisionId();
    PasswordRevisionId passwordRevisionId = new PasswordRevisionId();

    DeletedDateTime deletedDateTime = new DeletedDateTime();
    RevisedDateTime revisedDateTime = new RevisedDateTime();
    CreatedDateTime createdDateTime = new CreatedDateTime();

    public AccountRevision() {
    }

    public AccountRevision(AccountId accountId,
                           AccountNameRevisionId accountNameRevisionId,
                           EmailAddressRevisionId emailAddressRevisionId,
                           PasswordRevisionId passwordRevisionId,
                           DeletedDateTime deletedDateTime,
                           RevisedDateTime revisedDateTime,
                           CreatedDateTime createdDateTime) {
        this.accountId = accountId;
        this.accountNameRevisionId = accountNameRevisionId;
        this.emailAddressRevisionId = emailAddressRevisionId;
        this.passwordRevisionId = passwordRevisionId;
        this.deletedDateTime = deletedDateTime;
        this.revisedDateTime = revisedDateTime;
        this.createdDateTime = createdDateTime;
    }

    public AccountId accountId() {
        return accountId;
    }
}
