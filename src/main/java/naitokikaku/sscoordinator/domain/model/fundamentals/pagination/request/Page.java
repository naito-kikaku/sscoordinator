package naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class Page implements Serializable {
    Long value;

    public Page(String value) {
        this.value = Long.parseLong(value);
    }

    public Page(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }

    public Long asLong() {
        if(value == null) return 0L;
        return value;
    }
}
