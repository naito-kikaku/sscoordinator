package naitokikaku.sscoordinator.application.usecase.account.signup.complete;

import naitokikaku.sscoordinator.domain.model.account.transaction.signup.SignUpTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.signup.SignUpTransactionFactory;
import naitokikaku.sscoordinator.domain.model.account.transaction.signup.SignUpTransactionRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class RegisterSignUpTransaction {
    @Resource
    SignUpTransactionFactory signUpTransactionFactory;
    @Resource
    SignUpTransactionRepository accountTransactionRepository;

    @EventListener
    @Transactional
    public void execute(SignUpCompleteEvent event) {
        SignUpTransaction signUpTransaction = signUpTransactionFactory.create(event.account, event.revision);
        accountTransactionRepository.store(signUpTransaction);
    }
}
