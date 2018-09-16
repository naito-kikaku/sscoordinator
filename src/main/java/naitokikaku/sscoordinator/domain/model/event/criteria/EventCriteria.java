package naitokikaku.sscoordinator.domain.model.event.criteria;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.Page;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.PaginationRequest;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class EventCriteria implements Serializable {
    LikeEventNameOrDescription likeEventNameOrDescription = new LikeEventNameOrDescription();
    PaginationRequest pagination = new PaginationRequest();

    public EventCriteria() {
    }

    public EventCriteria(LikeEventNameOrDescription likeEventNameOrDescription) {
        this(likeEventNameOrDescription, new PaginationRequest());
    }

    public EventCriteria(LikeEventNameOrDescription likeEventNameOrDescription, PaginationRequest pagination) {
        this.likeEventNameOrDescription = likeEventNameOrDescription;
        this.pagination = pagination;
    }

    public LikeEventNameOrDescription likeEventNameOrDescription() {
        return likeEventNameOrDescription;
    }

    public PaginationRequest pagination() {
        return pagination;
    }

    public EventCriteria rewrite(Page page) {
        return new EventCriteria(likeEventNameOrDescription, pagination.rewrite(page));
    }
}
