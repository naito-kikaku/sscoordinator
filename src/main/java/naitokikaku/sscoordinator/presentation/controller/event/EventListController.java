package naitokikaku.sscoordinator.presentation.controller.event;

import naitokikaku.sscoordinator.application.usecase.event.SearchEvent;
import naitokikaku.sscoordinator.domain.model.event.criteria.EventCriteria;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshots;
import naitokikaku.sscoordinator.domain.model.fundamentals.pagination.request.Page;
import naitokikaku.sscoordinator.presentation.controller.IndexController;
import naitokikaku.sscoordinator.presentation.controller.common.Breadcrumb;
import naitokikaku.sscoordinator.presentation.controller.common.PageInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/event/list")
@SessionAttributes("eventCriteria")
public class EventListController {
    public static final PageInformation INFO
            = new PageInformation("Event List", "/event/list", "folder open");

    @ModelAttribute("pageInfo")
    public PageInformation pageInfo() {
        return EventListController.INFO;
    }

    @ModelAttribute("breadcrumb")
    public Breadcrumb breadcrumb() {
        return new Breadcrumb(Arrays.asList(
                IndexController.INFO,
                EventListController.INFO
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
    public String list(@ModelAttribute("eventCriteria") EventCriteria criteria,
                       @RequestParam("page") Page page, Model model) {
        EventCriteria rewrittenCriteria = criteria.rewrite(page);
        EventSnapshots events = searchEvent.execute(rewrittenCriteria);
        model.addAttribute("events", events);
        return "event/list";
    }
}
