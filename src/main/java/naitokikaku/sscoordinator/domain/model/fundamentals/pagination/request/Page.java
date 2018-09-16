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

    Long asLong() {
        if (value == null) return 0L;
        return value;
    }

    public boolean isFirstPage() {
        return asLong() == 1L;
    }

    public Page prev() {
        return new Page(asLong() - 1);
    }

    public Page next() {
        return new Page(asLong() + 1);
    }

    public boolean is(Page other) {
        return this.equals(other);
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
