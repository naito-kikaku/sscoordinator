package naitokikaku.sscoordinator.domain.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;

import javax.validation.Valid;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class Event implements Serializable {
    EventId id = new EventId();
    @Valid
    EventName name = new EventName();
    EventDescription description = new EventDescription();

    public Event() {
    }

    public Event(EventId id, EventName name, EventDescription description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public EventId id() {
        return id;
    }

    public EventName name() {
        return name;
    }

    public EventDescription description() {
        return description;
    }
}
