package naitokikaku.sscoordinator.infrastructure.datasource.account.transaction;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionType;
import naitokikaku.sscoordinator.domain.model.account.transaction.signup.SignUpTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountTransactionMapper {

    AccountTransactionId nextAccountTransactionId();

    void storeHeader(@Param("accountTransactionId") AccountTransactionId accountTransactionId,
                     @Param("accountId") AccountId accountId,
                     @Param("accountTransactionType") AccountTransactionType accountTransactionType);

    void storeSignUp(@Param("accountTransactionId") AccountTransactionId accountTransactionId,
                     @Param("signUpTransaction") SignUpTransaction signUpTransaction);
}
