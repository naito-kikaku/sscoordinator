package naitokikaku.sscoordinator.application.usecase.event;

import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventRepository;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshot;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshotRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class ReviseEvent {
    @Resource
    EventSnapshotRepository eventSnapshotRepository;
    @Resource
    EventRepository eventRepository;

    @Transactional
    public void execute(Event event) {
        EventSnapshot eventSnapshot = eventSnapshotRepository.get(event.id());
        if (!eventSnapshot.isUpdatedBy(event)) return;
        eventRepository.update(event);
    }
}
