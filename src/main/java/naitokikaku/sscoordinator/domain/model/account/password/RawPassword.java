package naitokikaku.sscoordinator.domain.model.account.password;

import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
public class RawPassword implements Serializable {
    @NotEmpty
    String value;

    public RawPassword() {
    }

    public RawPassword(String value) {
        this.value = value;
    }

    public boolean same(RawPassword other) {
        return this.equals(other);
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }
}
