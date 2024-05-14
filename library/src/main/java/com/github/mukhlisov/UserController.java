package com.github.mukhlisov;

import com.github.mukhlisov.dto.UserInfoDto;
import com.github.mukhlisov.dto.UserUpdateDto;
import com.github.mukhlisov.exceptions.UserAlreadyExistsException;
import com.github.mukhlisov.securityModule.SecurityService;
import com.github.mukhlisov.securityModule.security.JwtEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final static boolean IS_LOGGED_IN = true;

    @GetMapping
    public String userProfilePage(Principal principal, Model model) {
        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        UserInfoDto user = userService.findById(jwtPrincipal.getId());
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        model.addAttribute("user", user);
        return "user_profile";
    }
    @GetMapping("/update")
    public String updateProfilePage(Principal principal, Model model) {
        JwtEntity jwtPrincipal = securityService.convertPrincipal(principal);
        UserInfoDto user = userService.findById(jwtPrincipal.getId());
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        model.addAttribute("user", user);
        return "user_profile_update";
    }

    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute UserUpdateDto updateDto, BindingResult result,
                                Model model, RedirectAttributes redirect) {
        model.addAttribute("isLoggedIn", IS_LOGGED_IN);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            model.addAttribute("user", updateDto);
            return "user_profile_update";
        }
        try{
            userService.updateUser(updateDto);
            redirect.addFlashAttribute("message", "Данные обновлены");
            return "redirect:/profile";
        } catch (UserAlreadyExistsException e){
            model.addAttribute("userAlreadyExists", e.getMessage());
            model.addAttribute("user", updateDto);
            return "user_profile_update";
        }
    }
}
