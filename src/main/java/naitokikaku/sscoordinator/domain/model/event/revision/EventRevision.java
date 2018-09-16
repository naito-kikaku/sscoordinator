package naitokikaku.sscoordinator.domain.model.event.revision;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.CreatedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.RevisedDateTime;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class EventRevision implements Serializable {
    EventRevisionId revisionId = new EventRevisionId();
    EventRevisionNumber revisionNumber = new EventRevisionNumber();
    CreatedDateTime createdDateTime = new CreatedDateTime();
    RevisedDateTime revisedDateTime = new RevisedDateTime();
}
