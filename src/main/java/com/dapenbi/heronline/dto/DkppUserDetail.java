package com.dapenbi.heronline.dto;

import com.dapenbi.heronline.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DkppUserDetail extends BaseDto implements UserDetails {

    private final Users user;

    private final List<GrantedAuthority> authorities;

    public DkppUserDetail(final Users user) {
        this.user = user;

        List<GrantedAuthority> roles = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        roles.add(authority);

        authorities = Collections.unmodifiableList(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isActive();
    }

    @Override
    public boolean isEnabled() {
        return this.user.isActive();
    }

    public Users getUser() {
        return this.user;
    }
}