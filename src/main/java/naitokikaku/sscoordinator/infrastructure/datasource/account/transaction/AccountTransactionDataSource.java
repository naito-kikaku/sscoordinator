package naitokikaku.sscoordinator.infrastructure.datasource.account.transaction;

import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionId;
import naitokikaku.sscoordinator.domain.model.account.transaction.signup.SignUpTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.signup.SignUpTransactionRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountTransactionDataSource implements SignUpTransactionRepository {
    @Resource
    AccountTransactionMapper mapper;

    @Override
    public void store(SignUpTransaction signUpTransaction) {
        AccountTransactionId transactionId = mapper.nextAccountTransactionId();
        mapper.storeHeader(transactionId, signUpTransaction.accountId(), signUpTransaction.type());
        mapper.storeSignUp(transactionId, signUpTransaction);
    }
}
