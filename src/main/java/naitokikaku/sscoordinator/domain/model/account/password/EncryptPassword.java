package naitokikaku.sscoordinator.domain.model.account.password;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EncryptPassword implements Serializable {
    String value;

    public EncryptPassword() {
    }

    public EncryptPassword(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }

    public boolean same(EncryptPassword password) {
        return this.equals(password);
    }
}
