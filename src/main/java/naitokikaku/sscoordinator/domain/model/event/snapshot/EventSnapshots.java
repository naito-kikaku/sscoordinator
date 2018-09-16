package naitokikaku.sscoordinator.domain.model.event.snapshot;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.response.PaginationResponse;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@ToString
public class EventSnapshots implements Serializable {
    List<EventSnapshot> list = new ArrayList<>();
    PaginationResponse pagination = new PaginationResponse();

    public EventSnapshots() {
    }

    public EventSnapshots(List<EventSnapshot> list, PaginationResponse pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public List<EventSnapshot> asList() {
        return Collections.unmodifiableList(list);
    }

    public PaginationResponse pagination() {
        return pagination;
    }

    public long count() {
        return pagination.totalElementSize().asLong();
    }

    public boolean isEmpty() {
        return pagination.totalElementSize().isZero();
    }
}
