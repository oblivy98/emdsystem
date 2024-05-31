package com.cmi.emdsystem.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import com.cmi.emdsystem.model.User;

import lombok.Data;

@Data
public class UserPrincipal {
    private Integer userId;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Integer userId, String email, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.userId = userId;
        this.email = email;
        this.authorities = authorities;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = List.of();

        return new UserPrincipal(
                user.getUserId(),
                user.getEmailAddress(),
                authorities);
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }
}
