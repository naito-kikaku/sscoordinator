package naitokikaku.sscoordinator.presentation.controller.fundamentals.page;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class PageInformation implements Serializable {
    String pageName;
    String url;
    String iconName;

    public PageInformation(String pageName, String url, String iconName) {
        this.pageName = pageName;
        this.url = url;
        this.iconName = iconName;
    }

    public String pageName() {
        return pageName;
    }

    public String url() {
        return url;
    }

    public String iconName() {
        return iconName;
    }
}
