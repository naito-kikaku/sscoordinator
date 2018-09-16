package naitokikaku.sscoordinator.presentation.controller.event;

import naitokikaku.sscoordinator.application.usecase.event.ReviseEvent;
import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshot;
import naitokikaku.sscoordinator.domain.model.event.snapshot.EventSnapshotRepository;
import naitokikaku.sscoordinator.presentation.controller.IndexPageInfo;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.Breadcrumb;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/event/{id}/edit")
@SessionAttributes("event")
public class EventEditFormController {

    @ModelAttribute("pageInfo")
    public PageInfo pageInfo(@PathVariable("id") EventId eventId) {
        return new EventEditFormPageInfo(eventId);
    }

    @ModelAttribute("breadcrumb")
    public Breadcrumb breadcrumb(@PathVariable("id") EventId eventId) {
        return new Breadcrumb(Arrays.asList(
                new IndexPageInfo(),
                new EventListFormPageInfo(),
                new EventEditFormPageInfo(eventId)
        ));
    }

    @Resource
    EventSnapshotRepository eventSnapshotRepository;

    @GetMapping
    public String index(@PathVariable("id") EventId eventId, Model model) {
        EventSnapshot eventSnapshot = eventSnapshotRepository.get(eventId);
        Event event = eventSnapshot.asEvent();
        model.addAttribute("event", event);
        return "redirect:/event/" + eventId + "/edit?editing";
    }

    @GetMapping(params = "editing")
    public String editing(@ModelAttribute("event") Event event) {
        return "event/edit";
    }

    @Resource
    ReviseEvent reviseEvent;

    @PostMapping
    public String post(@Valid @ModelAttribute("event") Event event, BindingResult binding, SessionStatus status) {
        if (binding.hasErrors()) return "event/edit";
        reviseEvent.execute(event);
        status.setComplete();
        return "redirect:/event/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(allowFields);
    }

    private static String[] allowFields = new String[]{
            "name.value"
    };

}
