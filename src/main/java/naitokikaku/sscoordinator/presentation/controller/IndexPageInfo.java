package naitokikaku.sscoordinator.presentation.controller;

import lombok.EqualsAndHashCode;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.IconName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageURL;

import java.io.Serializable;

@EqualsAndHashCode
public class IndexPageInfo implements Serializable, PageInfo {
    PageName pageName = new PageName("Home");
    PageURL pageURL = new PageURL("/");
    IconName iconName = new IconName("home");

    @Override
    public PageName pageName() {
        return pageName;
    }

    @Override
    public PageURL pageURL() {
        return pageURL;
    }

    @Override
    public IconName iconName() {
        return iconName;
    }
}
