package naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class PageElementSize implements Serializable {
    Long value;

    public PageElementSize(String value) {
        this.value = Long.parseLong(value);
    }

    public PageElementSize(Long value) {
        this.value = value;
    }

    public boolean isZero() {
        return asDouble() == 0d;
    }

    public Double asDouble() {
        if (value == null) return 0d;
        return value.doubleValue();
    }

    Long asLong() {
        if (value == null) return 0L;
        return value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
