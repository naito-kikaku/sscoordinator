package naitokikaku.sscoordinator.domain.model.fundamentals.pagination.response;

import lombok.EqualsAndHashCode;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.Page;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.PageElementSize;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@EqualsAndHashCode
public class TotalPageSize implements Serializable {
    Long value;

    TotalPageSize(Long value) {
        this.value = value;
    }

    static TotalPageSize of(TotalElementSize totalElementSize, PageElementSize pageElementSize) {
        if (pageElementSize.isZero()) throw new IllegalArgumentException();
        Double totalPageSize = Math.ceil(totalElementSize.asDouble() / pageElementSize.asDouble());
        return new TotalPageSize(totalPageSize.longValue());
    }

    public List<Page> pageSequence() {
        return LongStream.rangeClosed(1, value).boxed()
                .map(Page::new)
                .collect(Collectors.toList());
    }

    public Page asPage() {
        return new Page(value);
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value.toString();
    }
}
