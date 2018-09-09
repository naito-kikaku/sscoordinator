package naitokikaku.sscoordinator.domain.model.event.revision;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EventRevisionNumber implements Serializable {
    Long value;

    public EventRevisionNumber() {
    }

    public EventRevisionNumber(Long value) {
        this.value = value;
    }

    public static EventRevisionNumber first() {
        return new EventRevisionNumber(1L);
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
