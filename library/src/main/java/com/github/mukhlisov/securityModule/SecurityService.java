package com.github.mukhlisov.securityModule;

import com.github.mukhlisov.securityModule.security.JwtEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SecurityService {
    public JwtEntity convertPrincipal(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken authenticationToken) {
            return (JwtEntity) authenticationToken.getPrincipal();
        } else {
            throw new RuntimeException("Unknown principal type");
        }
    }
}
