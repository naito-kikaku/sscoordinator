package naitokikaku.sscoordinator.domain.model.event.criteria;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@EqualsAndHashCode
public class LikeEventNameOrDescription implements Serializable {
    String value;

    public LikeEventNameOrDescription() {
    }

    public LikeEventNameOrDescription(String value) {
        this.value = value;
    }

    public String queryText() {
        return "%" + value + "%";
    }

    public boolean hasValue() {
        return !StringUtils.isEmpty(value);
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }
}
