package naitokikaku.sscoordinator.domain.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class Event implements Serializable {
    @Valid
    EventName name = new EventName();

    public Event() {
    }

    public Event(EventName name) {
        this.name = name;
    }
}
