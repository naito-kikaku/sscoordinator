package naitokikaku.sscoordinator.domain.model.account;

import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@EqualsAndHashCode
public class AccountName implements Serializable {
    @NotEmpty
    @Size(max = 128)
    String value;

    public AccountName() {
    }

    public AccountName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }

    public boolean same(AccountName other) {
        return this.equals(other);
    }
}
