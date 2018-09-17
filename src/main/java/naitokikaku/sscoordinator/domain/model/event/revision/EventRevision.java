package naitokikaku.sscoordinator.domain.model.event.revision;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.CreatedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.RevisedDateTime;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class EventRevision implements Serializable {
    EventRevisionId revisionId = new EventRevisionId();
    EventRevisionNumber revisionNumber = new EventRevisionNumber();
    RevisedDateTime revisedDateTime = new RevisedDateTime();
    CreatedDateTime createdDateTime = new CreatedDateTime();

    public String asText() {
        String createdAt = String.format("Created at %s", createdDateTime.format());
        if (revisionNumber.isFirst()) return String.format("%s, %s", revisionNumber.asText(), createdAt);
        String lastRevisedAt = String.format("Last Revised at %s", revisedDateTime.format());
        return String.format("%s, %s, %s", revisionNumber.asText(), lastRevisedAt, createdAt);
    }
}
