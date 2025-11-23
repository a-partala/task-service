package net.partala.taskservice.auth;

import lombok.Getter;
import net.partala.taskservice.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class SecurityUser {

    @Getter
    private final Long id;
    @Getter
    private final Set<UserRole> roles;

    public SecurityUser(Long id, Set<UserRole> roles) {
        this.id = id;
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getAuthority()))
                .toList();
    }
}
