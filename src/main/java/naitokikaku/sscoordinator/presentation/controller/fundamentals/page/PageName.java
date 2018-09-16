package naitokikaku.sscoordinator.presentation.controller.fundamentals.page;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class PageName implements Serializable {
    String value;

    public PageName() {
    }

    public PageName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == null) return "";
        return value;
    }
}
