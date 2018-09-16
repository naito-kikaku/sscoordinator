package naitokikaku.sscoordinator.application.usecase.event;

import naitokikaku.sscoordinator.domain.model.event.EventRepository;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshot;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshotRepository;
import naitokikaku.sscoordinator.domain.model.event.status.EventStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ReopenEvent {
    @Resource
    EventSnapshotRepository eventSnapshotRepository;
    @Resource
    EventRepository eventRepository;

    @Transactional
    public void execute(EventId eventId) {
        EventSnapshot eventSnapshot = eventSnapshotRepository.get(eventId);
        EventStatus status = eventSnapshot.eventStatus();
        if (status.isOpen()) return;
        eventRepository.reopen(eventId);
    }
}
