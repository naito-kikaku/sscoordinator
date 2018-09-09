package naitokikaku.sscoordinator.presentation.controller.admin.event;

import naitokikaku.sscoordinator.application.usecase.admin.event.RegisterEvent;
import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/event/register")
@SessionAttributes("event")
public class EventRegisterFormController {

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
        if (binding.hasErrors()) return "event/register";
        registerEvent.execute(event);
        status.setComplete();
        return "redirect:/";
    }

    private static String[] allowFields = new String[]{
            "event.name.value"
    };
}
