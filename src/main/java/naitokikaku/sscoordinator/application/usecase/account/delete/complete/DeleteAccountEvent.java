package naitokikaku.sscoordinator.application.usecase.account.delete.complete;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import org.springframework.context.ApplicationEvent;

public class DeleteAccountEvent extends ApplicationEvent {
    Account account;
    AccountRevision revision;

    public DeleteAccountEvent(Object source, Account account, AccountRevision revision) {
        super(source);
        this.account = account;
        this.revision = revision;
    }
}
