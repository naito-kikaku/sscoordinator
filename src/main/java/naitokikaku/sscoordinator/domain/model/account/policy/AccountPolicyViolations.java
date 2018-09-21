package naitokikaku.sscoordinator.domain.model.account.policy;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
public class AccountPolicyViolations implements Serializable {
    List<AccountPolicyViolation> list = new ArrayList<>();

    AccountPolicyViolations() {
    }

    AccountPolicyViolations(List<AccountPolicyViolation> list) {
        this.list = list;
    }

    public boolean isNothing() {
        return list.isEmpty();
    }

    public List<String> messages() {
        return list.stream()
                .map(AccountPolicyViolation::message)
                .collect(Collectors.toList());
    }
}
