package naitokikaku.sscoordinator.presentation.controller.event;

import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshot;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshotRepository;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.Breadcrumb;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import naitokikaku.sscoordinator.presentation.controller.home.HomePageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/event/{id}")
public class EventDetailController {

    @ModelAttribute("pageInfo")
    public PageInfo pageInfo(@PathVariable("id") EventId eventId) {
        return new EventDetailPageInfo(eventId);
    }

    @ModelAttribute("breadcrumb")
    public Breadcrumb breadcrumb(@PathVariable("id") EventId eventId) {
        return new Breadcrumb(Arrays.asList(
                new HomePageInfo(),
                new EventListPageInfo(),
                new EventDetailPageInfo(eventId)
        ));
    }

    @Resource
    EventSnapshotRepository eventSnapshotRepository;

    @GetMapping
    public String index(@PathVariable("id") EventId eventId,
                        @ModelAttribute("successMessage") String successMessage, Model model) {
        EventSnapshot eventSnapshot = eventSnapshotRepository.get(eventId);
        model.addAttribute("event", eventSnapshot);
        model.addAttribute("successMessage", successMessage);
        return "event/detail";
    }

}
