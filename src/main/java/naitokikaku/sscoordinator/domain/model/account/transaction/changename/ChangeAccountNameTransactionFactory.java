package naitokikaku.sscoordinator.domain.model.account.transaction.changename;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import org.springframework.stereotype.Component;

@Component
public class ChangeAccountNameTransactionFactory {

    public ChangeAccountNameTransaction create(AccountId accountId, AccountRevision revision) {
        return new ChangeAccountNameTransaction(accountId, revision);
    }
}
