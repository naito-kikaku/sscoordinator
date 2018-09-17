package naitokikaku.sscoordinator.domain.model.event.revision;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class EventRevisionNumber implements Serializable {
    Long value;

    public EventRevisionNumber() {
    }

    public EventRevisionNumber(Long value) {
        this.value = value;
    }

    public static EventRevisionNumber first() {
        return new EventRevisionNumber(1L);
    }

    public boolean isFirst() {
        return value == 1L;
    }

    public String asTextWithPrefix() {
        return "v" + value.toString();
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }


}
