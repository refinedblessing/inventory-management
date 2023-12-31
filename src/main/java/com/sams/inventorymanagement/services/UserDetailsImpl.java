package com.sams.inventorymanagement.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.entities.Store;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    @Getter
    private Long id;
    @Getter
    private final String email;
    private final String username;
    @Getter
    private final String firstName;
    @Getter
    private final String lastName;

    @JsonIgnore
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;
    @Getter
    private Set<Store> stores;

    public UserDetailsImpl(Long id, String username, String email, String firstName, String lastName, String password,
                           Collection<? extends GrantedAuthority> authorities, Set<Store> stores) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authorities = authorities;
        this.stores = stores;
    }
    public static UserDetailsImpl build(AppUser user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                authorities,
                user.getStores()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
