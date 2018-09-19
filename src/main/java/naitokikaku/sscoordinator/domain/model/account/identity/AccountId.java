package naitokikaku.sscoordinator.domain.model.account.identity;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class AccountId implements Serializable {
    Long value;

    public AccountId() {
    }

    public AccountId(String value) {
        this.value = Long.valueOf(value);
    }

    public AccountId(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
