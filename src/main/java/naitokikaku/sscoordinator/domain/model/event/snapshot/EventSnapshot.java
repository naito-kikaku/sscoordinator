package naitokikaku.sscoordinator.domain.model.event.snapshot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.event.EventName;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.revision.EventRevision;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class EventSnapshot implements Serializable {
    EventId eventId = new EventId();
    EventName eventName = new EventName();
    EventRevision revision = new EventRevision();
}
