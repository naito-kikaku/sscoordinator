package naitokikaku.sscoordinator.domain.model.event.identity;

import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@EqualsAndHashCode
public class EventId implements Serializable {
    Long value;

    public EventId() {
    }

    public EventId(String value) {
        this.value = Long.valueOf(value);
    }

    public EventId(Long value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(value);
    }

    public String asText() {
        if (value == null) return "";
        return "#" + value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
