package naitokikaku.sscoordinator.domain.model.event;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class Event implements Serializable {
    EventName name = new EventName();

    public Event() {
    }

    public Event(EventName name) {
        this.name = name;
    }
}
