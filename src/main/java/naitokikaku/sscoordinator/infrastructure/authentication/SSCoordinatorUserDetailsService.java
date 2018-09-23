package naitokikaku.sscoordinator.infrastructure.authentication;

import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshotRepository;
import naitokikaku.sscoordinator.domain.model.account.EmailAddress;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SSCoordinatorUserDetailsService implements UserDetailsService {
    @Resource
    AccountSnapshotRepository accountSnapshotRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountSnapshot accountSnapshot = accountSnapshotRepository.findBy(new EmailAddress(username));
        if (accountSnapshot != null)
            return new SSCoordinatorUserDetails(accountSnapshot, AuthorityUtils.createAuthorityList("USER"));
        throw new UsernameNotFoundException("not found : " + username);
    }
}
