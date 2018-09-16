package naitokikaku.sscoordinator.application.usecase.event;

import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshotRepository;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshots;
import naitokikaku.sscoordinator.domain.model.event.criteria.EventCriteria;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SearchEvent {
    @Resource
    EventSnapshotRepository eventSnapshotRepository;

    public EventSnapshots execute(EventCriteria criteria) {
        return eventSnapshotRepository.findBy(criteria);
    }

}
