package naitokikaku.sscoordinator.presentation.controller.event;

import lombok.EqualsAndHashCode;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.IconName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageURL;

import java.io.Serializable;

@EqualsAndHashCode
public class EventRegisterFormPageInfo implements Serializable, PageInfo {
    PageName pageName = new PageName("Event Register");
    PageURL pageURL = new PageURL("/event/register");
    IconName iconName = new IconName("book");

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
