package naitokikaku.sscoordinator.domain.model.event.status;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EventStatus implements Serializable {
    EventStatusType value;

    public EventStatus() {
    }

    public EventStatus(EventStatusType value) {
        this.value = value;
    }

    public boolean isClosed() {
        return value == EventStatusType.CLOSED;
    }

    public boolean isOpen() {
        return value == EventStatusType.OPEN;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
