package naitokikaku.sscoordinator.domain.model.account.transaction.delete;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import org.springframework.stereotype.Component;

@Component
public class DeleteAccountTransactionFactory {

    public DeleteAccountTransaction create(AccountId id, AccountRevision revision) {
        return new DeleteAccountTransaction(id, revision);
    }
}
