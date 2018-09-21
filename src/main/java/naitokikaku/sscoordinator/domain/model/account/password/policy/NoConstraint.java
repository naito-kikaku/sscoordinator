package naitokikaku.sscoordinator.domain.model.account.password.policy;

import naitokikaku.sscoordinator.domain.model.account.password.RawPassword;
import org.springframework.stereotype.Component;

@Component
public class NoConstraint implements PasswordPolicy {

    @Override
    public PasswordPolicyViolation valid(RawPassword rawPassword) {
        return new PasswordPolicyViolation(rawPassword);
    }
}
