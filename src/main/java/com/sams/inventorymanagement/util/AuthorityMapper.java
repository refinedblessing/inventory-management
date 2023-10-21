package com.sams.inventorymanagement.util;

import com.sams.inventorymanagement.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
public class AuthorityMapper {
    public static Set<UserRole> mapAuthoritiesToUserRole(Collection<? extends GrantedAuthority> authorities) {
        Set<UserRole> roles = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(UserRole.ROLE_ADMIN.name())) {
                roles.add(UserRole.ROLE_ADMIN);
            } else if (authority.getAuthority().equals(UserRole.ROLE_STORE_MANAGER.name())) {
                roles.add(UserRole.ROLE_STORE_MANAGER);
            } else if (authority.getAuthority().equals(UserRole.ROLE_STORE_STAFF.name())) {
                roles.add(UserRole.ROLE_STORE_STAFF);
            }
        }
        return roles;
    }
}
