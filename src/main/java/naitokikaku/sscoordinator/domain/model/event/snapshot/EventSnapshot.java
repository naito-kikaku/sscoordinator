package naitokikaku.sscoordinator.domain.model.event.snapshot;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventName;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.revision.EventRevision;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class EventSnapshot implements Serializable {
    EventId eventId = new EventId();
    EventName eventName = new EventName();
    EventRevision revision = new EventRevision();

    public EventId eventId() {
        return eventId;
    }

    public EventName eventName() {
        return eventName;
    }

    public EventRevision revision() {
        return revision;
    }

    public Event asEvent() {
        return new Event(eventId, eventName);
    }

    public boolean isUpdatedBy(Event event) {
        return !this.eventName.equals(event.name());
    }
}
