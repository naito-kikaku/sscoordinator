package naitokikaku.sscoordinator.presentation.controller.signup;

import lombok.EqualsAndHashCode;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.IconName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageName;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageURL;

import java.io.Serializable;

@EqualsAndHashCode
public class SignUpPageInfo implements Serializable, PageInfo {
    PageName pageName = new PageName("SignUp");
    PageURL pageURL = new PageURL("/signup");
    IconName iconName = new IconName("user plus");

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
