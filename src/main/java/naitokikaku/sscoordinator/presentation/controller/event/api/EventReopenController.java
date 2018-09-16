package naitokikaku.sscoordinator.presentation.controller.event.api;

import naitokikaku.sscoordinator.application.usecase.event.ReopenEvent;
import naitokikaku.sscoordinator.domain.model.event.identity.EventId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/event/{id}/reopen")
public class EventReopenController {
    @Resource
    ReopenEvent reopenEvent;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void post(@PathVariable("id") EventId eventId) {
        reopenEvent.execute(eventId);
    }
}
