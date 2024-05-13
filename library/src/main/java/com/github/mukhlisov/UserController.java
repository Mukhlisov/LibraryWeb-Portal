package com.github.mukhlisov;

import com.github.mukhlisov.dto.UserProfileInfo;
import com.github.mukhlisov.securityModule.SecurityService;
import com.github.mukhlisov.securityModule.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping
    public String userProfilePage(Principal principal, Model model) {
        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        UserProfileInfo user = userService.findById(jwtPrincipal.getId());
        model.addAttribute("isLoggedIn", true);
        model.addAttribute("user", user);
        return "user_profile";
    }
    @GetMapping("/update")
    public String updateProfilePage(Principal principal, Model model) {
        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        UserProfileInfo user = userService.findById(jwtPrincipal.getId());
        model.addAttribute("isLoggedIn", true);
        model.addAttribute("user", user);
        return "user_profile_update";
    }
}
