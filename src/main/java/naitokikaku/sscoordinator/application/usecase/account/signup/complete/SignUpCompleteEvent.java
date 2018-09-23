package naitokikaku.sscoordinator.application.usecase.account.signup.complete;

import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import org.springframework.context.ApplicationEvent;

public class SignUpCompleteEvent extends ApplicationEvent {
    Account account;
    AccountRevision revision;

    public SignUpCompleteEvent(Object source, Account account, AccountRevision revision) {
        super(source);
        this.account = account;
        this.revision = revision;
    }
}
