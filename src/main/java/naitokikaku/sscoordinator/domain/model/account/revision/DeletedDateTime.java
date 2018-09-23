package naitokikaku.sscoordinator.domain.model.account.revision;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode
public class DeletedDateTime implements Serializable {
    LocalDateTime value;

    public DeletedDateTime() {
    }

    public DeletedDateTime(LocalDateTime value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
