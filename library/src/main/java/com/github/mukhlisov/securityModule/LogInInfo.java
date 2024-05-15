package com.github.mukhlisov.securityModule;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LogInInfo {
    private final boolean isLoggedIn;

    @Autowired
    public LogInInfo(HttpServletRequest request){
        this.isLoggedIn = request.getUserPrincipal() != null;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }
}
