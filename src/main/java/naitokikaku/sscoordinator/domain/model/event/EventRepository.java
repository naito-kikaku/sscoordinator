package naitokikaku.sscoordinator.domain.model.event;

import naitokikaku.sscoordinator.domain.model.event.identity.EventId;

public interface EventRepository {

    void store(Event event);

    void update(Event event);

    void close(EventId eventId);

    void reopen(EventId eventId);
}
