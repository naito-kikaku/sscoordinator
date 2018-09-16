package naitokikaku.sscoordinator.domain.model.event;

public interface EventRepository {

    void store(Event event);

    void update(Event event);
}
