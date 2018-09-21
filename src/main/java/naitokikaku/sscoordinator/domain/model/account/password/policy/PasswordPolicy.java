package naitokikaku.sscoordinator.domain.model.account.password.policy;

import naitokikaku.sscoordinator.domain.model.account.password.RawPassword;

public interface PasswordPolicy {

    PasswordPolicyViolation valid(RawPassword rawPassword);

}
