package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.JwtResponse;
import com.sams.inventorymanagement.dto.LogInRequest;
import com.sams.inventorymanagement.dto.SignUpRequest;
import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.enums.UserRole;
import com.sams.inventorymanagement.exceptions.EntityDuplicateException;
import com.sams.inventorymanagement.services.AppUserService;
import com.sams.inventorymanagement.services.UserDetailsImpl;
import com.sams.inventorymanagement.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AppUserService appUserService,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LogInRequest logInRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getUsername(), logInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtResponse res = new JwtResponse();
        res.setToken(jwt);
        res.setId(userDetails.getId());
        res.setUsername(userDetails.getUsername());
        res.setRoles(roles);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUser> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (appUserService.existsByEmail(signUpRequest.getEmail())) {
            throw new EntityDuplicateException("Email is already taken");
        }

        AppUser user = new AppUser();
        user.setEmail(signUpRequest.getEmail());
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(hashedPassword);
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());

//        TODO remove role addition from here
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.ROLE_ADMIN);
//        roles.add(UserRole.ROLE_STORE_MANAGER);
//        roles.add(UserRole.ROLE_STORE_STAFF);
        user.setRoles(roles);

        AppUser createdUser = appUserService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
