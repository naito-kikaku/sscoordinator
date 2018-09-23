package naitokikaku.sscoordinator.infrastructure.authenticate;

import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SSCoordinatorSecurityContext {

    public AccountId accountId() {
        SecurityContext context = SecurityContextHolder.getContext();
        SSCoordinatorUserDetails userDetails = (SSCoordinatorUserDetails) context.getAuthentication().getPrincipal();
        return userDetails.accountId();
    }
}
