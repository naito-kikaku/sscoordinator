package naitokikaku.sscoordinator.domain.model.event;

import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@EqualsAndHashCode
public class EventName implements Serializable {
    @NotEmpty
    @Size(max = 128)
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
