package naitokikaku.sscoordinator.domain.model.account.transaction;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class AccountTransactionId implements Serializable {
    Long value;

    public AccountTransactionId() {
    }

    public AccountTransactionId(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
