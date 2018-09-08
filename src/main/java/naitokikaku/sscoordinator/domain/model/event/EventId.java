package naitokikaku.sscoordinator.domain.model.event;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EventId implements Serializable {
    Long value;

    public EventId() {
    }

    public EventId(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
