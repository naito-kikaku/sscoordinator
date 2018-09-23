package naitokikaku.sscoordinator.presentation.controller.authentication;

import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(params = "error")
    public String error(@ModelAttribute("emailAddress") String inputEmailAddress, Model model) {
        model.addAttribute("emailAddress", inputEmailAddress);
        model.addAttribute("errorMessage", "メールアドレスまたはパスワードが違います");
        return "login";
    }
}
