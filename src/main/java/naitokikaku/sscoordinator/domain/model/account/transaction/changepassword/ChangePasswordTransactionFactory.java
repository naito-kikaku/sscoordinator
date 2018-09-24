package naitokikaku.sscoordinator.domain.model.account.transaction.changepassword;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.transaction.changeemail.ChangeEmailAddressTransaction;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordTransactionFactory {

    public ChangePasswordTransaction create(AccountId accountId, AccountRevision revision) {
        return new ChangePasswordTransaction(accountId, revision);
    }
}
