package naitokikaku.sscoordinator.presentation.controller.login;

import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginFormController {

    @ModelAttribute("pageInfo")
    public PageInfo pageInfo() {
        return new LoginPageInfo();
    }

    @GetMapping
    public String index() {
        return "login";
    }
}
