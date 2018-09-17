package naitokikaku.sscoordinator.domain.model.event.status;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
public class ClosedDateTime implements Serializable {
    LocalDateTime value;

    public ClosedDateTime() {
    }

    public ClosedDateTime(LocalDateTime value) {
        this.value = value;
    }

    public String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return value.format(formatter);
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
