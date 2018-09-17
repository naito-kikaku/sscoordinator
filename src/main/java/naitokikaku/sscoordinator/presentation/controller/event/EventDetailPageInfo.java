package naitokikaku.sscoordinator.presentation.controller.event;

import lombok.EqualsAndHashCode;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.IconName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageURL;

import java.io.Serializable;

@EqualsAndHashCode
public class EventDetailPageInfo implements Serializable, PageInfo {
    PageName pageName;
    PageURL pageURL;
    IconName iconName;

    EventDetailPageInfo(EventId eventId) {
        this(new PageName("Event Detail"),
                new PageURL("/event/" + eventId),
                new IconName("book"));
    }

    private EventDetailPageInfo(PageName pageName, PageURL pageURL, IconName iconName) {
        this.pageName = pageName;
        this.pageURL = pageURL;
        this.iconName = iconName;
    }

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
