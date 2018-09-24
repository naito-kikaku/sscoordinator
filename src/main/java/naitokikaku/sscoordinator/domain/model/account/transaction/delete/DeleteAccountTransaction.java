package naitokikaku.sscoordinator.domain.model.account.transaction.delete;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionType;

import java.io.Serializable;

public class DeleteAccountTransaction implements Serializable {
    AccountTransactionId transactionId = new AccountTransactionId();
    AccountId accountId = new AccountId();
    AccountRevision accountRevision = new AccountRevision();

    DeleteAccountTransaction() {
    }

    DeleteAccountTransaction(AccountId accountId, AccountRevision accountRevision) {
        this(new AccountTransactionId(), accountId, accountRevision);
    }

    DeleteAccountTransaction(AccountTransactionId transactionId, AccountId accountId, AccountRevision accountRevision) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.accountRevision = accountRevision;
    }

    public AccountTransactionType type() {
        return AccountTransactionType.DELETE;
    }

    public AccountId accountId() {
        return accountId;
    }
}
