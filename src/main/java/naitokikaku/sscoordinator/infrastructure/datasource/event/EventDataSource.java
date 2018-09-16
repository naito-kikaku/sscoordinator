package naitokikaku.sscoordinator.infrastructure.datasource.event;

import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventRepository;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.revision.EventRevisionId;
import naitokikaku.sscoordinator.domain.model.event.revision.EventRevisionNumber;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class EventDataSource implements EventRepository {
    @Resource
    EventMapper mapper;

    @Override
    public void store(Event event) {
        EventId eventId = mapper.nextEventId();
        mapper.store(eventId);
        EventRevisionId eventRevisionId = mapper.nextEventRevisionId();
        EventRevisionNumber eventRevisionNumber = EventRevisionNumber.first();
        mapper.storeRevision(eventRevisionId, eventId, eventRevisionNumber, event);
        mapper.storeLatestPointer(eventId, eventRevisionId, eventRevisionNumber);
    }

    @Override
    public void update(Event event) {
        EventRevisionId eventRevisionId = mapper.nextEventRevisionId();
        EventRevisionNumber eventRevisionNumber = mapper.nextEventRevisionNumber(event);
        mapper.storeRevision(eventRevisionId, event.id(), eventRevisionNumber, event);
        mapper.deleteLatestPointer(event);
        mapper.storeLatestPointer(event.id(), eventRevisionId, eventRevisionNumber);
    }
}
