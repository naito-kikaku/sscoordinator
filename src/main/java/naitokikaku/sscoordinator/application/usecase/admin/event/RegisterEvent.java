package naitokikaku.sscoordinator.application.usecase.admin.event;

import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class RegisterEvent {
    @Resource
    EventRepository eventRepository;

    @Transactional
    public void execute(Event event) {
        eventRepository.store(event);
    }
}
