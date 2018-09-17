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

    public String asHTMLText() {
        if (value == null) return "";
        return value.replaceAll("\\r\\n|\\r|\\n", "<br/>");
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }
}
