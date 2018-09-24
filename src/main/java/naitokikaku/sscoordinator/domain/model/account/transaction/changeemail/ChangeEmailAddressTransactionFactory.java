package naitokikaku.sscoordinator.domain.model.account.transaction.changeemail;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.transaction.changename.ChangeAccountNameTransaction;
import org.springframework.stereotype.Component;

@Component
public class ChangeEmailAddressTransactionFactory {

    public ChangeEmailAddressTransaction create(AccountId accountId, AccountRevision revision) {
        return new ChangeEmailAddressTransaction(accountId, revision);
    }
}
