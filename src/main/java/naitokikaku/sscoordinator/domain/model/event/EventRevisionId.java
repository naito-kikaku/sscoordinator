package naitokikaku.sscoordinator.domain.model.event;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EventRevisionId implements Serializable {
    Long value;

    public EventRevisionId() {
    }

    public EventRevisionId(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
