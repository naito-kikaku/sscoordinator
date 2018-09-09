package naitokikaku.sscoordinator.domain.model.event;

import org.springframework.stereotype.Component;

@Component
public class EventFactory {

    public Event create() {
        return new Event();
    }
}
