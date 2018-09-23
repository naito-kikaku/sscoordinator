package naitokikaku.sscoordinator.application.usecase.account.login.success;

import naitokikaku.sscoordinator.domain.model.account.transaction.login.LoginTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.login.LoginTransactionFactory;
import naitokikaku.sscoordinator.domain.model.account.transaction.login.LoginTransactionRepository;
import naitokikaku.sscoordinator.infrastructure.authenticate.SSCoordinatorUserDetails;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class RegisterLoginTransaction {
    @Resource
    LoginTransactionFactory loginTransactionFactory;
    @Resource
    LoginTransactionRepository loginTransactionRepository;

    @EventListener
    @Transactional
    public void execute(AuthenticationSuccessEvent event) {
        SSCoordinatorUserDetails userDetails = (SSCoordinatorUserDetails) event.getAuthentication().getPrincipal();
        LoginTransaction loginTransaction = loginTransactionFactory.create(userDetails.accountId());
        loginTransactionRepository.store(loginTransaction);
    }
}
