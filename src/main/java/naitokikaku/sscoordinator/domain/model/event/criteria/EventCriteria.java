package naitokikaku.sscoordinator.domain.model.event.criteria;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.Page;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.PaginationRequest;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class EventCriteria implements Serializable {
    LikeEventName likeEventName = new LikeEventName();
    PaginationRequest pagination = new PaginationRequest();

    public EventCriteria() {
    }

    public EventCriteria(LikeEventName likeEventName) {
        this(likeEventName, new PaginationRequest());
    }

    public EventCriteria(LikeEventName likeEventName, PaginationRequest pagination) {
        this.likeEventName = likeEventName;
        this.pagination = pagination;
    }

    public LikeEventName likeEventName() {
        return likeEventName;
    }

    public PaginationRequest pagination() {
        return pagination;
    }

    public EventCriteria rewrite(Page page) {
        return new EventCriteria(likeEventName, pagination.rewrite(page));
    }
}
