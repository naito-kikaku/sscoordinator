package naitokikaku.sscoordinator.presentation.controller.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class PageInformation implements Serializable {
    String pageName;
    String url;
    String iconName;

    public PageInformation(String pageName, String url, String iconName) {
        this.pageName = pageName;
        this.url = url;
        this.iconName = iconName;
    }
}
