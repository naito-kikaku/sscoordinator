package naitokikaku.sscoordinator.application.usecase.account.changename.complete;

import naitokikaku.sscoordinator.domain.model.account.transaction.changename.ChangeAccountNameTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.changename.ChangeAccountNameTransactionFactory;
import naitokikaku.sscoordinator.domain.model.account.transaction.changename.ChangeAccountNameTransactionRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class RegisterChangeAccountNameTransaction {
    @Resource
    ChangeAccountNameTransactionFactory changeAccountNameTransactionFactory;
    @Resource
    ChangeAccountNameTransactionRepository changeAccountNameTransactionRepository;

    @EventListener
    @Transactional
    public void execute(ChangeAccountNameCompleteEvent event) {
        ChangeAccountNameTransaction transaction = changeAccountNameTransactionFactory.create(event.account.id(), event.revision);
        changeAccountNameTransactionRepository.store(transaction);
    }
}
