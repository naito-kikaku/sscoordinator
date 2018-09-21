package naitokikaku.sscoordinator.domain.model.account.password.policy;

import lombok.EqualsAndHashCode;
import naitokikaku.sscoordinator.domain.model.account.password.RawPassword;

import java.io.Serializable;

@EqualsAndHashCode
public class PasswordPolicyViolation implements Serializable {
    String message;
    RawPassword source;

    PasswordPolicyViolation() {
    }

    PasswordPolicyViolation(RawPassword rawPassword) {
        this(null, rawPassword);
    }

    PasswordPolicyViolation(String message, RawPassword source) {
        this.message = message;
        this.source = source;
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
