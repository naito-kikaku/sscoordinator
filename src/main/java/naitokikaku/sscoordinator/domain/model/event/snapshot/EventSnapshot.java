package naitokikaku.sscoordinator.domain.model.event.snapshot;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventDescription;
import naitokikaku.sscoordinator.domain.model.event.EventName;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.revision.EventRevision;
import naitokikaku.sscoordinator.domain.model.event.status.EventStatus;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class EventSnapshot implements Serializable {
    EventId eventId = new EventId();
    EventName eventName = new EventName();
    EventDescription eventDescription = new EventDescription();
    EventStatus eventStatus = new EventStatus();
    EventRevision revision = new EventRevision();

    public EventId eventId() {
        return eventId;
    }

    public EventName eventName() {
        return eventName;
    }

    public EventDescription eventDescription() {
        return eventDescription;
    }

    public EventRevision revision() {
        return revision;
    }

    public EventStatus eventStatus() {
        return eventStatus;
    }

    public Event asEvent() {
        return new Event(eventId, eventName, eventDescription);
    }

    public boolean isUpdatedBy(Event event) {
        return !this.eventName.equals(event.name())
                || !this.eventDescription.equals(event.description());
    }
}
