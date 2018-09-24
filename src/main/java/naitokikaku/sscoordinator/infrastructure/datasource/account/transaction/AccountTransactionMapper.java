package naitokikaku.sscoordinator.infrastructure.datasource.account.transaction;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionId;
import naitokikaku.sscoordinator.domain.model.account.transaction.AccountTransactionType;
import naitokikaku.sscoordinator.domain.model.account.transaction.changeemail.ChangeEmailAddressTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.changename.ChangeAccountNameTransaction;
import naitokikaku.sscoordinator.domain.model.account.transaction.changepassword.ChangePasswordTransaction;
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

    void storeChangeName(@Param("accountTransactionId") AccountTransactionId accountTransactionId,
                         @Param("changeAccountNameTransaction") ChangeAccountNameTransaction changeAccountNameTransaction);

    void storeChangeEmailAddress(@Param("accountTransactionId") AccountTransactionId accountTransactionId,
                                 @Param("changeEmailAddressTransaction") ChangeEmailAddressTransaction changeEmailAddressTransaction);

    void storeChangePassword(@Param("accountTransactionId") AccountTransactionId accountTransactionId,
                             @Param("changePasswordTransaction") ChangePasswordTransaction changePasswordTransaction);
}
