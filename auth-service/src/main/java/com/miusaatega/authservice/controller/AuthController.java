package com.miusaatega.authservice.controller;

import com.miusaatega.authservice.config.security.jwt.JwtUtil;
import com.miusaatega.authservice.models.AuthenticationRequest;
import com.miusaatega.authservice.models.AuthenticationResponse;
import com.miusaatega.authservice.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Authentication")
class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        System.out.println(authenticationRequest);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyAuthenticationToken(@RequestBody() AuthenticationResponse authResponse) {
        String token = authResponse.getToken();
        System.out.println("token: " + token);
        String username;
        try {
            username = jwtTokenUtil.extractUsername(token);
            System.out.println("Username: " + username);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid Token");
        }

        return ResponseEntity.ok(new HashMap<String, String>(){{
            put("id", username);
        }});
    }
}

