package naitokikaku.sscoordinator.domain.model.account.transaction.login;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import org.springframework.stereotype.Component;

@Component
public class LoginTransactionFactory {

    public LoginTransaction create(AccountId accountId) {
        return new LoginTransaction(accountId);
    }
}
