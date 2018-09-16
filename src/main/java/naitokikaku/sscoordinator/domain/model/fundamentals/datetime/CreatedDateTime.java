package naitokikaku.sscoordinator.domain.model.fundamentals.datetime;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
public class CreatedDateTime implements Serializable {
    LocalDateTime value;

    public CreatedDateTime() {
    }

    public CreatedDateTime(LocalDateTime value) {
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
