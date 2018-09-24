package naitokikaku.sscoordinator.application.usecase.account.delete.complete;

import naitokikaku.sscoordinator.domain.model.account.transaction.delete.DeleteAccountTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.delete.DeleteAccountTransactionFactory;
import naitokikaku.sscoordinator.domain.model.account.transaction.delete.DeleteAccountTransactionRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class RegisterDeleteAccountTransaction {
    @Resource
    DeleteAccountTransactionFactory deleteAccountTransactionFactory;
    @Resource
    DeleteAccountTransactionRepository deleteAccountTransactionRepository;

    @EventListener
    @Transactional
    public void execute(DeleteAccountEvent event) {
        DeleteAccountTransaction transaction = deleteAccountTransactionFactory.create(event.account.id(), event.revision);
        deleteAccountTransactionRepository.store(transaction);
    }
}
