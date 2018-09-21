package naitokikaku.sscoordinator.domain.model.account.policy;

import lombok.EqualsAndHashCode;
import naitokikaku.sscoordinator.domain.model.account.Account;

import java.io.Serializable;

@EqualsAndHashCode
public class AccountPolicyViolation implements Serializable {
    Account source;
    String message;

    AccountPolicyViolation() {
    }

    AccountPolicyViolation(String message) {
        this(null, message);
    }

    public AccountPolicyViolation(Account source, String message) {
        this.source = source;
        this.message = message;
    }

    public boolean isNothing() {
        return message == null;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        if (message == null) return "";
        return message;
    }
}
