package naitokikaku.sscoordinator.domain.model.fundamentals.datetime;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
public class RevisedDateTime implements Serializable, Comparable<RevisedDateTime> {
    LocalDateTime value;

    public RevisedDateTime() {
    }

    public RevisedDateTime(LocalDateTime value) {
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

    @Override
    public int compareTo(RevisedDateTime o) {
        return value.compareTo(o.value);
    }
}
