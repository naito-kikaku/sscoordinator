package naitokikaku.sscoordinator.domain.model.event.snapshot;

import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.criteria.EventCriteria;

public interface EventSnapshotRepository {

    EventSnapshot get(EventId eventId);

    EventSnapshots findBy(EventCriteria criteria);
}
