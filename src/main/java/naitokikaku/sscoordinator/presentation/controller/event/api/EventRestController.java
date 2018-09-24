package naitokikaku.sscoordinator.presentation.controller.event.api;

import naitokikaku.sscoordinator.application.usecase.event.CloseEvent;
import naitokikaku.sscoordinator.application.usecase.event.ReopenEvent;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/event/{id}")
public class EventRestController {
    @Resource
    CloseEvent closeEvent;

    @PostMapping("/close")
    @ResponseStatus(HttpStatus.OK)
    public void close(@PathVariable("id") EventId eventId) {
        closeEvent.execute(eventId);
    }

    @Resource
    ReopenEvent reopenEvent;

    @PostMapping("/reopen")
    @ResponseStatus(HttpStatus.OK)
    public void reopen(@PathVariable("id") EventId eventId) {
        reopenEvent.execute(eventId);
    }
}
