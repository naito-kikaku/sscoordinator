package naitokikaku.sscoordinator.domain.model.account.revision;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class PasswordRevisionId implements Serializable {
    Long value;

    public PasswordRevisionId() {
    }

    public PasswordRevisionId(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
