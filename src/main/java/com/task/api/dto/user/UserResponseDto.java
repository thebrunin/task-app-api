package com.task.api.dto.user;

import com.task.api.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserResponseDto(
        String id,
        String email,
        String name,
        Collection<? extends GrantedAuthority> authorities,
        boolean enable,
        boolean accountNonExpired
) {
    public UserResponseDto(User entity) {
        this(entity.getId(), entity.getEmail(), entity.getName(), entity.getAuthorities(), entity.isEnabled(), entity.isAccountNonExpired());
    }
}
