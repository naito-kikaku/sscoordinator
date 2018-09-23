package naitokikaku.sscoordinator.domain.model.account.status;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class AccountStatus implements Serializable {
    EnableClass value;

    public AccountStatus() {
    }

    public AccountStatus(EnableClass value) {
        this.value = value;
    }

    public boolean isEnable() {
        return value == EnableClass.ENABLE;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
