package com.sams.inventorymanagement.dto;

import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Data
@NoArgsConstructor
public class AppUserDTO {
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;

    private Set<UserRole> roles;
    private Set<StoreDTO> stores;

    public AppUserDTO(AppUser user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles();
        this.stores = user.getStores()
                .stream()
                .map(StoreDTO::fromStore)
                .collect(Collectors.toSet());
    }

    public boolean isAdmin() {
        return this.roles.contains(UserRole.ROLE_ADMIN);
    }

    public static AppUserDTO fromAppUser(AppUser user) {
        return new AppUserDTO(user);
    }
}
