package naitokikaku.sscoordinator.domain.model.account.transaction.changepassword;

public interface ChangePasswordTransactionRepository {

    void store(ChangePasswordTransaction changePasswordTransaction);
}
