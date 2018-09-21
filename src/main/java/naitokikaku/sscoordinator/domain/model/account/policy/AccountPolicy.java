package naitokikaku.sscoordinator.domain.model.account.policy;

import naitokikaku.sscoordinator.domain.model.account.Account;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountPolicy {
    @Resource
    NotDuplicateEmailAddress notDuplicateEmailAddress;

    public boolean ok(Account account) {
        return notDuplicateEmailAddress.ok(account);
    }

    public AccountPolicyViolations valid(Account account) {
        List<AccountPolicyViolation> violations = new ArrayList<>();
        AccountPolicyViolation notDuplicateEmailAddressViolation = notDuplicateEmailAddress.valid(account);
        if (!notDuplicateEmailAddressViolation.isNothing()) violations.add(notDuplicateEmailAddressViolation);
        return new AccountPolicyViolations(violations);
    }
}
