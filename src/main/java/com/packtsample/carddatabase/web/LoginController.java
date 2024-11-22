package com.packtsample.carddatabase.web;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packtsample.carddatabase.domain.AccountCredentials;
import com.packtsample.carddatabase.domain.AppUser;
import com.packtsample.carddatabase.service.JwtService;

@RestController
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService; 

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager,
    UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {
        // Generate token and send it in the response Authorization
        // header
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.username(),
                credentials.password());
        Authentication auth = authenticationManager.authenticate(creds);

          UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.username());
           AppUser appUser = (AppUser) userDetails;
           String role = appUser.getRole();
           String jwt = jwtService.getToken(auth.getName());
        // Generate token
        String jwts = jwtService.getToken(auth.getName());
        // Build response with the generated token
        return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
        .body(Map.of("token", jwt, "role", role));
    }

}
