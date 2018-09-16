package naitokikaku.sscoordinator.presentation.controller;

import naitokikaku.sscoordinator.presentation.controller.fundamentals.Breadcrumb;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @ModelAttribute("pageInfo")
    public PageInfo pageInfo() {
        return new IndexPageInfo();
    }

    @ModelAttribute("breadcrumb")
    public Breadcrumb breadcrumb() {
        return new Breadcrumb();
    }

    @GetMapping
    public String index() {
        return "fragment/appframe";
    }
}
