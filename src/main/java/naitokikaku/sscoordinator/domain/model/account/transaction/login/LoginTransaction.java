package naitokikaku.sscoordinator.domain.model.account.transaction.login;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionType;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class LoginTransaction implements Serializable {
    AccountTransactionId transactionId = new AccountTransactionId();
    AccountId accountId = new AccountId();

    LoginTransaction() {
    }

    LoginTransaction(AccountId accountId) {
        this(new AccountTransactionId(), accountId);
    }

    LoginTransaction(AccountTransactionId transactionId, AccountId accountId) {
        this.transactionId = transactionId;
        this.accountId = accountId;
    }

    public AccountTransactionType type() {
        return AccountTransactionType.LOGIN;
    }

    public AccountId accountId() {
        return accountId;
    }
}
