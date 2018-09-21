package naitokikaku.sscoordinator.domain.model.account.status;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
public class ArchivedDateTime implements Serializable {
    LocalDateTime value;

    public ArchivedDateTime() {
    }

    public ArchivedDateTime(LocalDateTime value) {
        this.value = value;
    }

    public String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return value.format(formatter);
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
