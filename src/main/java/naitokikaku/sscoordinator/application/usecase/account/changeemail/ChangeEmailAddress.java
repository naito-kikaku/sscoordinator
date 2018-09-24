package naitokikaku.sscoordinator.application.usecase.account.changeemail;

import naitokikaku.sscoordinator.application.usecase.account.changeemail.complete.ChangeEmailAddressCompleteEvent;
import naitokikaku.sscoordinator.domain.model.account.Account;
import naitokikaku.sscoordinator.domain.model.account.AccountRepository;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.revision.AccountRevision;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ChangeEmailAddress {
    @Resource
    AccountRepository accountRepository;
    @Resource
    ApplicationEventPublisher publisher;

    @Transactional
    public void execute(AccountSnapshot snapshot, EmailAddress emailAddress) {
        if (emailAddress.same(snapshot.emailAddress())) return;
        accountRepository.deleteActive(snapshot.emailAddress());
        accountRepository.storeActive(emailAddress);
        AccountRevision updatedRevision = accountRepository.update(emailAddress);

        Account updatedAccount = snapshot.account().replace(emailAddress);
        publisher.publishEvent(new ChangeEmailAddressCompleteEvent(this, updatedAccount, updatedRevision));
    }
}
