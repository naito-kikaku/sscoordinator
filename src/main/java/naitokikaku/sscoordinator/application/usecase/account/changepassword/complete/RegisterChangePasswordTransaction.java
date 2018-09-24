package naitokikaku.sscoordinator.application.usecase.account.changepassword.complete;

import naitokikaku.sscoordinator.domain.model.account.transaction.changepassword.ChangePasswordTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.changepassword.ChangePasswordTransactionFactory;
import naitokikaku.sscoordinator.domain.model.account.transaction.changepassword.ChangePasswordTransactionRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class RegisterChangePasswordTransaction {
    @Resource
    ChangePasswordTransactionFactory changePasswordTransactionFactory;
    @Resource
    ChangePasswordTransactionRepository changePasswordTransactionRepository;

    @EventListener
    @Transactional
    public void execute(ChangePasswordCompleteEvent event) {
        ChangePasswordTransaction transaction = changePasswordTransactionFactory.create(event.account.id(), event.revision);
        changePasswordTransactionRepository.store(transaction);
    }
}
