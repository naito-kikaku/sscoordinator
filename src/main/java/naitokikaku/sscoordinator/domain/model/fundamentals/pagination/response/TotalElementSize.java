package naitokikaku.sscoordinator.domain.model.fundamentals.pagination.response;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class TotalElementSize implements Serializable {
    Long value;

    public TotalElementSize(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }

    Double asDouble() {
        if (value == null) return 0d;
        return value.doubleValue();
    }

    public Long asLong() {
        if (value == null) return 0L;
        return value;
    }

    public boolean isZero() {
        return asLong() == 0L;
    }
}
