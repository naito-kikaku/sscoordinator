package naitokikaku.sscoordinator.infrastructure.datasource.account.transaction;

import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionId;
import naitokikaku.sscoordinator.domain.model.account.transaction.changeemail.ChangeEmailAddressTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.changeemail.ChangeEmailAddressTransactionRepository;
import naitokikaku.sscoordinator.domain.model.account.transaction.changename.ChangeAccountNameTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.changename.ChangeAccountNameTransactionRepository;
import naitokikaku.sscoordinator.domain.model.account.transaction.changepassword.ChangePasswordTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.changepassword.ChangePasswordTransactionRepository;
import naitokikaku.sscoordinator.domain.model.account.transaction.signup.SignUpTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.signup.SignUpTransactionRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountTransactionDataSource implements SignUpTransactionRepository,
        ChangeAccountNameTransactionRepository, ChangeEmailAddressTransactionRepository, ChangePasswordTransactionRepository {
    @Resource
    AccountTransactionMapper mapper;

    @Override
    public void store(SignUpTransaction transaction) {
        AccountTransactionId transactionId = mapper.nextAccountTransactionId();
        mapper.storeHeader(transactionId, transaction.accountId(), transaction.type());
        mapper.storeSignUp(transactionId, transaction);
    }

    @Override
    public void store(ChangeAccountNameTransaction transaction) {
        AccountTransactionId transactionId = mapper.nextAccountTransactionId();
        mapper.storeHeader(transactionId, transaction.accountId(), transaction.type());
        mapper.storeChangeName(transactionId, transaction);
    }

    @Override
    public void store(ChangeEmailAddressTransaction transaction) {
        AccountTransactionId transactionId = mapper.nextAccountTransactionId();
        mapper.storeHeader(transactionId, transaction.accountId(), transaction.type());
        mapper.storeChangeEmailAddress(transactionId, transaction);
    }

    @Override
    public void store(ChangePasswordTransaction transaction) {
        AccountTransactionId transactionId = mapper.nextAccountTransactionId();
        mapper.storeHeader(transactionId, transaction.accountId(), transaction.type());
        mapper.storeChangePassword(transactionId, transaction);
    }
}
