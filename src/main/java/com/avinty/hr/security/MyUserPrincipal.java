package com.avinty.hr.security;

import com.avinty.hr.entity.EmployeeEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class MyUserPrincipal implements UserDetails {
    private String user;
    private String pass;
    private String claims;
    private long id;
    private boolean enabled;

    public MyUserPrincipal() {
        //
    }

    public MyUserPrincipal(EmployeeEntity userEntity) {
        user = userEntity.getEmail();
        id = userEntity.getId();
        pass = userEntity.getPassword();
        enabled = userEntity.getActive();
        claims = userEntity.getClaims();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (claims == null) {
            return Collections.EMPTY_LIST;
        }
        return Arrays.stream(claims.split(";")).map(x -> new SimpleGrantedAuthority(x)).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return user;
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
        return enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}