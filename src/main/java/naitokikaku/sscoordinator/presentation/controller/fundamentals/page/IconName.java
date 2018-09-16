package naitokikaku.sscoordinator.presentation.controller.fundamentals.page;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class IconName implements Serializable {
    String value;

    public IconName() {
    }

    public IconName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }
}
