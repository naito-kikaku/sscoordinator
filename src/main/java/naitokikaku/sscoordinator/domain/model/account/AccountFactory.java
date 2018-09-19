package naitokikaku.sscoordinator.domain.model.account;

import naitokikaku.sscoordinator.domain.model.fundamentals.email.EmailAddress;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public Account create(AccountName accountName, EmailAddress emailAddress) {
        return new Account(accountName, emailAddress);
    }
}
