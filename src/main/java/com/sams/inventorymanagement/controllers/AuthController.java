package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.JwtResponse;
import com.sams.inventorymanagement.dto.LogInRequest;
import com.sams.inventorymanagement.dto.SignUpRequest;
import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.services.AppUserService;
import com.sams.inventorymanagement.services.UserDetailsImpl;
import com.sams.inventorymanagement.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        TODO find user in db before authenticating
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getUsername(), logInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtResponse res = new JwtResponse();

        String jwt = jwtUtil.generateJwtToken(authentication);
        res.setToken(jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        res.setUser(new AppUser(userDetails));

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        AppUser user = new AppUser(signUpRequest);
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(hashedPassword);

        AppUser createdUser = appUserService.createUser(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtResponse res = new JwtResponse();

        String jwt = jwtUtil.generateJwtToken(authentication);
        res.setToken(jwt);
        res.setUser(createdUser);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
