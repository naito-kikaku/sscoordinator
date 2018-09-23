package naitokikaku.sscoordinator.domain.model.account;

import naitokikaku.sscoordinator.domain.model.account.password.EncryptPassword;
import naitokikaku.sscoordinator.domain.model.account.password.RawPassword;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountFactory {
    @Resource
    PasswordEncoder passwordEncoder;

    public Account create(AccountName accountName, EmailAddress emailAddress, RawPassword rawPassword) {
        String encryptPassword = passwordEncoder.encode(rawPassword.toString());
        return new Account(accountName, emailAddress, new EncryptPassword(encryptPassword));
    }
}
