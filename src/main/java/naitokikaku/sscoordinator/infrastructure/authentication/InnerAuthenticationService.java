package naitokikaku.sscoordinator.infrastructure.authentication;

import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import naitokikaku.sscoordinator.domain.model.account.identity.AccountId;
import naitokikaku.sscoordinator.domain.model.account.password.RawPassword;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InnerAuthenticationService {
    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    SSCoordinatorUserDetailsService userDetailsService;

    public boolean authenticate(EmailAddress emailAddress, RawPassword rawPassword) {
        SSCoordinatorUserDetails userDetails = (SSCoordinatorUserDetails) userDetailsService.loadUserByUsername(emailAddress.toString());
        Authentication token = new UsernamePasswordAuthenticationToken(userDetails, rawPassword.toString(), userDetails.authorities);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextImpl context = new SecurityContextImpl();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        return authentication.isAuthenticated();
    }
}
