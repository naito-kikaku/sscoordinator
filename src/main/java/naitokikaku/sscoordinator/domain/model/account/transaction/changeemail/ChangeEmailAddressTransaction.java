package naitokikaku.sscoordinator.domain.model.account.transaction.changeemail;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionType;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class ChangeEmailAddressTransaction implements Serializable {
    AccountTransactionId transactionId = new AccountTransactionId();
    AccountId accountId = new AccountId();
    AccountRevision accountRevision = new AccountRevision();

    ChangeEmailAddressTransaction() {
    }

    ChangeEmailAddressTransaction(AccountId accountId, AccountRevision accountRevision) {
        this(new AccountTransactionId(), accountId, accountRevision);
    }

    ChangeEmailAddressTransaction(AccountTransactionId transactionId, AccountId accountId, AccountRevision accountRevision) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.accountRevision = accountRevision;
    }

    public AccountTransactionType type() {
        return AccountTransactionType.CHANGE_ACCOUNT_NAME;
    }

    public AccountId accountId() {
        return accountId;
    }
}
