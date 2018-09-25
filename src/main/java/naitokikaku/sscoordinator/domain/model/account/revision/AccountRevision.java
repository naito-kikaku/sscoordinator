package naitokikaku.sscoordinator.domain.model.account.revision;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.CreatedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.DeletedDateTime;
import naitokikaku.sscoordinator.domain.model.fundamentals.datetime.RevisedDateTime;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class AccountRevision implements Serializable {
    CreatedDateTime createdDateTime = new CreatedDateTime();
    RevisedDateTime revisedDateTime = new RevisedDateTime();
    DeletedDateTime deletedDateTime = new DeletedDateTime();

    public AccountRevision() {
    }

    public AccountRevision(DeletedDateTime deletedDateTime,
                           RevisedDateTime revisedDateTime,
                           CreatedDateTime createdDateTime) {
        this.deletedDateTime = deletedDateTime;
        this.revisedDateTime = revisedDateTime;
        this.createdDateTime = createdDateTime;
    }
}
