package naitokikaku.sscoordinator.application.usecase.account.signup;

import naitokikaku.sscoordinator.application.usecase.account.signup.complete.SignUpCompleteEvent;
import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.policy.AccountPolicy;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class SignUp {
    @Resource
    AccountPolicy accountPolicy;
    @Resource
    AccountRepository accountRepository;
    @Resource
    ApplicationEventPublisher publisher;

    @Transactional
    public void execute(Account account) {
        if (!accountPolicy.ok(account)) throw new IllegalArgumentException();

        accountRepository.storeActive(account.emailAddress());
        AccountRevision storedRevision = accountRepository.store(account);

        publisher.publishEvent(new SignUpCompleteEvent(this, account, storedRevision));
    }
}
