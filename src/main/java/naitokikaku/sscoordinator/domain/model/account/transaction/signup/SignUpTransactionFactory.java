package naitokikaku.sscoordinator.domain.model.account.transaction.signup;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import org.springframework.stereotype.Component;

@Component
public class SignUpTransactionFactory {

    public SignUpTransaction create(Account account, AccountRevision revision) {
        return new SignUpTransaction(account, revision);
    }
}
