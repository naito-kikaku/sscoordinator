package naitokikaku.sscoordinator.application.usecase.event;

import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventRepository;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class RegisterEvent {
    @Resource
    EventRepository eventRepository;

    @Transactional
    public EventId execute(Event event) {
        return eventRepository.store(event);
    }
}
