package naitokikaku.sscoordinator.domain.model.account.transaction.login;

public interface LoginTransactionRepository {

    void store(LoginTransaction loginTransaction);
}
