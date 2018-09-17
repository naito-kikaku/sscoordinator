package naitokikaku.sscoordinator.domain.model.event.status;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class EventStatus implements Serializable {
    EventStatusType type;
    ClosedDateTime closedDateTime = new ClosedDateTime();

    public EventStatus() {
    }

    public EventStatus(EventStatusType type, ClosedDateTime closedDateTime) {
        this.type = type;
        this.closedDateTime = closedDateTime;
    }

    public boolean isClosed() {
        return type == EventStatusType.CLOSED;
    }

    public boolean isOpen() {
        return type == EventStatusType.OPEN;
    }

    public String asText() {
        if (isOpen()) return "Open";
        return String.format("Closed at %s", closedDateTime.format());
    }
}
