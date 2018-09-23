package naitokikaku.sscoordinator.presentation.controller.event;

import naitokikaku.sscoordinator.application.usecase.event.SearchEvent;
import naitokikaku.sscoordinator.domain.model.event.criteria.EventCriteria;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshots;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.Page;
import naitokikaku.sscoordinator.presentation.controller.home.HomePageInfo;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.Breadcrumb;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/event/list")
@SessionAttributes({"eventCriteria"})
public class EventListController {

    @ModelAttribute("pageInfo")
    public PageInfo pageInfo() {
        return new EventListPageInfo();
    }

    @ModelAttribute("breadcrumb")
    public Breadcrumb breadcrumb() {
        return new Breadcrumb(Arrays.asList(
                new HomePageInfo(),
                new EventListPageInfo()
        ));
    }

    @ModelAttribute("eventCriteria")
    public EventCriteria criteria() {
        return new EventCriteria();
    }

    @GetMapping
    public String index() {
        return "redirect:/event/list?page=1";
    }

    @Resource
    SearchEvent searchEvent;

    @GetMapping(params = {"page"})
    public String list(@ModelAttribute("eventCriteria") EventCriteria criteria, @RequestParam("page") Page page, Model model) {
        EventCriteria rewrittenCriteria = criteria.rewrite(page);
        EventSnapshots events = searchEvent.execute(rewrittenCriteria);
        model.addAttribute("events", events);
        return "event/list";
    }

    @PostMapping
    public String search(@ModelAttribute("eventCriteria") EventCriteria criteria, Model model) {
        EventSnapshots events = searchEvent.execute(criteria);
        model.addAttribute("events", events);
        return "redirect:/event/list?page=" + criteria.pagination().currentPage();
    }

    @GetMapping(params = {"clear"})
    public String clear(Model model) {
        model.addAttribute("eventCriteria", criteria());
        return "redirect:/event/list";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(allowFields);
    }

    private static String[] allowFields = new String[]{
            "likeEventNameOrDescription.value"
    };
}
