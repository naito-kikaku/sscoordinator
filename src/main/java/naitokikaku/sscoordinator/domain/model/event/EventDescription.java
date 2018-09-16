package naitokikaku.sscoordinator.domain.model.event;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EventDescription implements Serializable {
    String value;

    public EventDescription() {
    }

    public EventDescription(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }
}
