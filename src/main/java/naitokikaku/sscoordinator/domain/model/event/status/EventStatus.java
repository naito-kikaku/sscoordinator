package naitokikaku.sscoordinator.domain.model.event.status;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class EventStatus implements Serializable {
    OpenClass value;
    ClosedDateTime closedDateTime = new ClosedDateTime();

    public EventStatus() {
    }

    public EventStatus(OpenClass value, ClosedDateTime closedDateTime) {
        this.value = value;
        this.closedDateTime = closedDateTime;
    }

    public boolean isClosed() {
        return value == OpenClass.CLOSED;
    }

    public boolean isOpen() {
        return value == OpenClass.OPEN;
    }

    public String asText() {
        if (isOpen()) return "Open";
        return String.format("Closed at %s", closedDateTime.format());
    }
}
