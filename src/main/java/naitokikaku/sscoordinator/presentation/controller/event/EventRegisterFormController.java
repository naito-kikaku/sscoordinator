package naitokikaku.sscoordinator.presentation.controller.event;

import naitokikaku.sscoordinator.application.usecase.event.RegisterEvent;
import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventFactory;
import naitokikaku.sscoordinator.presentation.controller.IndexController;
import naitokikaku.sscoordinator.presentation.controller.common.Breadcrumb;
import naitokikaku.sscoordinator.presentation.controller.common.PageInformation;
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
@RequestMapping("/event/register")
@SessionAttributes("event")
public class EventRegisterFormController {
    public static final PageInformation INFO
            = new PageInformation("Event Register", "/event/register", "book");

    @ModelAttribute("pageInfo")
    public PageInformation pageInfo() {
        return EventRegisterFormController.INFO;
    }

    @ModelAttribute("breadcrumb")
    public Breadcrumb breadcrumb() {
        return new Breadcrumb(Arrays.asList(
                IndexController.INFO,
                EventRegisterFormController.INFO
        ));
    }

    @Resource
    EventFactory eventFactory;

    @GetMapping
    public String index(Model model) {
        Event event = eventFactory.create();
        model.addAttribute("event", event);
        return "redirect:/event/register?editing";
    }

    @GetMapping(params = "editing")
    public String editing(@ModelAttribute("event") Event event) {
        return "event/register";
    }

    @Resource
    RegisterEvent registerEvent;

    @PostMapping
    public String post(@Valid @ModelAttribute("event") Event event, BindingResult binding, SessionStatus status) {
        if (binding.hasErrors()) return "admin/event/register";
        registerEvent.execute(event);
        status.setComplete();
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(allowFields);
    }

    private static String[] allowFields = new String[]{
            "name.value"
    };
}