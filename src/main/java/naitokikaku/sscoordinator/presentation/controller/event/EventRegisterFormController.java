package naitokikaku.sscoordinator.presentation.controller.event;

import naitokikaku.sscoordinator.application.usecase.event.RegisterEvent;
import naitokikaku.sscoordinator.domain.model.event.Event;
import naitokikaku.sscoordinator.domain.model.event.EventFactory;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import naitokikaku.sscoordinator.presentation.controller.IndexPageInfo;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.Breadcrumb;
import naitokikaku.sscoordinator.presentation.controller.fundamentals.page.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/event/register")
@SessionAttributes("event")
public class EventRegisterFormController {

    @ModelAttribute("pageInfo")
    public PageInfo pageInfo() {
        return new EventRegisterFormPageInfo();
    }

    @ModelAttribute("breadcrumb")
    public Breadcrumb breadcrumb() {
        return new Breadcrumb(Arrays.asList(
                new IndexPageInfo(),
                new EventListPageInfo(),
                new EventRegisterFormPageInfo()
        ));
    }

    @Resource
    EventFactory eventFactory;

    @GetMapping
    public String index(@ModelAttribute("successMessage") String successMessage,
                        RedirectAttributes attributes, Model model) {
        Event event = eventFactory.create();
        model.addAttribute("event", event);
        attributes.addFlashAttribute("successMessage", successMessage);
        return "redirect:/event/register?editing";
    }

    @GetMapping(params = "editing")
    public String editing(@ModelAttribute("event") Event event,
                          @ModelAttribute("successMessage") String successMessage, Model model) {
        model.addAttribute("successMessage", successMessage);
        return "event/register";
    }

    @Resource
    RegisterEvent registerEvent;

    @PostMapping(params = "return")
    public String post(@Valid @ModelAttribute("event") Event event, BindingResult binding,
                       RedirectAttributes attributes, SessionStatus status) {
        if (binding.hasErrors()) return "event/register";
        EventId eventId = registerEvent.execute(event);
        status.setComplete();
        attributes.addFlashAttribute("successMessage", "イベントを登録しました。");
        return "redirect:/event/" + eventId;
    }

    @PostMapping(params = "continuous")
    public String continuousPost(@Valid @ModelAttribute("event") Event event, BindingResult binding,
                                 RedirectAttributes attributes, SessionStatus status) {
        if (binding.hasErrors()) return "event/register";
        EventId eventId = registerEvent.execute(event);
        status.setComplete();
        String successMessage = String.format("イベント<a href=\"/event/%s\">%s</a>を登録しました。", eventId, eventId.asText());
        attributes.addFlashAttribute("successMessage", successMessage);
        return "redirect:/event/register";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(allowFields);
    }

    private static String[] allowFields = new String[]{
            "name.value",
            "description.value"
    };
}
