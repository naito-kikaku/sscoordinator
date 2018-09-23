package naitokikaku.sscoordinator.infrastructure.authenticate;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import naitokikaku.sscoordinator.domain.model.account.snapshot.AccountSnapshot;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode
@ToString
public class SSCoordinatorUserDetails implements UserDetails {
    AccountSnapshot accountSnapshot;
    List<GrantedAuthority> authorities;

    SSCoordinatorUserDetails(AccountSnapshot accountSnapshot, List<GrantedAuthority> authorities) {
        this.accountSnapshot = accountSnapshot;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return accountSnapshot.account().password().toString();
    }

    @Override
    public String getUsername() {
        return accountSnapshot.account().emailAddress().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return accountSnapshot.accountStatus().isEnable();
    }
}
