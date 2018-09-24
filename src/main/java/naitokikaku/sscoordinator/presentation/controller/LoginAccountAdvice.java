package naitokikaku.sscoordinator.presentation.controller;

import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

// accessible after login
@ControllerAdvice(basePackages = {
        "naitokikaku.sscoordinator.presentation.controller.home"
        , "naitokikaku.sscoordinator.presentation.controller.event"
})
public class LoginAccountAdvice {

    @Resource
    AccountSnapshotRepository accountSnapshotRepository;

    @ModelAttribute("account")
    public AccountSnapshot account() {
        return accountSnapshotRepository.get();
    }

}
