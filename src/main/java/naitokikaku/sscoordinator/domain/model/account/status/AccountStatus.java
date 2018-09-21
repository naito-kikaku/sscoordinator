package naitokikaku.sscoordinator.domain.model.account.status;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class AccountStatus implements Serializable {
    AccountStatusType type;
    ArchivedDateTime archivedDateTime = new ArchivedDateTime();

    public AccountStatus() {
    }

    public AccountStatus(AccountStatusType type, ArchivedDateTime archivedDateTime) {
        this.type = type;
        this.archivedDateTime = archivedDateTime;
    }
}
