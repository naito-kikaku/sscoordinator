package naitokikaku.sscoordinator.domain.model.account;

import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
public class EmailAddress implements Serializable {
    @NotEmpty
    @Email
    String value;

    public EmailAddress() {
    }

    public EmailAddress(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }
}
