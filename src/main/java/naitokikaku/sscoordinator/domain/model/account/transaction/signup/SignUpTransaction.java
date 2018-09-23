package naitokikaku.sscoordinator.domain.model.account.transaction.signup;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionType;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class SignUpTransaction implements Serializable {
    AccountTransactionId transactionId = new AccountTransactionId();
    Account account = new Account();
    AccountRevision accountRevision = new AccountRevision();

    SignUpTransaction() {
    }

    SignUpTransaction(Account account, AccountRevision accountRevision) {
        this(new AccountTransactionId(), account, accountRevision);
    }

    SignUpTransaction(AccountTransactionId transactionId, Account account, AccountRevision accountRevision) {
        this.transactionId = transactionId;
        this.account = account;
        this.accountRevision = accountRevision;
    }

    public AccountId accountId() {
        return accountRevision.accountId();
    }

    public AccountTransactionType type() {
        return AccountTransactionType.SIGN_UP;
    }
}
