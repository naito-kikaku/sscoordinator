package naitokikaku.sscoordinator.domain.model.account.revision;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EmailAddressRevisionId implements Serializable {
    Long value;

    public EmailAddressRevisionId() {
    }

    public EmailAddressRevisionId(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
