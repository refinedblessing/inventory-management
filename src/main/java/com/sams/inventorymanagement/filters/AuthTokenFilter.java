package com.sams.inventorymanagement.filters;

import com.sams.inventorymanagement.services.UserDetailsServiceImpl;
import com.sams.inventorymanagement.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
@Order(2)
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthTokenFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isLoginOrSignupRequest(request)) {
            // Skip token validation for login and signup
            filterChain.doFilter(request, response);
            return;
        }

        try {
            logger.error(request.getContextPath());
            logger.error(request.getPathInfo());
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
                String username = jwtUtil.getUserNameFromJwtToken(jwt);
//               TODO check for user in DB before doing this
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // Handle exceptions
            log.error("Error while processing authentication: {}", e);

            // You can choose to send a specific error response here
            // For example, return a custom error JSON response
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: Internal Server Error");
        }
    }


    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    private boolean isLoginOrSignupRequest(HttpServletRequest request) {
        // Check if the URL matches login and signup endpoints
        String requestURI = request.getRequestURI();
        return requestURI.equals("/api/auth/login") || requestURI.equals("/api/auth/signup");
    }
}
