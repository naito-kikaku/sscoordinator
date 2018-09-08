package naitokikaku.sscoordinator.domain.model.event;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EventName implements Serializable {
    String value;

    public EventName() {
    }

    public EventName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }
}
