package naitokikaku.sscoordinator.presentation.controller.account;

import lombok.EqualsAndHashCode;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.IconName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageURL;

import java.io.Serializable;

@EqualsAndHashCode
public class MyAccountProfilePageInfo implements Serializable, PageInfo {
    PageName pageName = new PageName("My Account");
    PageURL pageURL = new PageURL("/myaccount");
    IconName iconName = new IconName("user");

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
