package com.github.mukhlisov;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.mukhlisov.dto.UserRegDto;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    private static boolean containsOnlyLetters(String text){
        for (int i = 0; i < text.length(); i++) {
            if ((int)text.charAt(i) < 1040 || (int)text.charAt(i) > 1103){
                return false;
            }
        }
        return true;
    }

    private static boolean validateTextField(String text){
        if (text.isEmpty()){
            return false;
        } else return containsOnlyLetters(text);
    }

    private static Boolean isValid(UserRegDto userDto){
        boolean message = validateTextField(userDto.getFirstName());
        if (!message){
            return message;
        }
        message = validateTextField(userDto.getLastName());
        if (!message){
            return message;
        }
        if (userDto.getPhoneNumber().length() != 11){
            return false;
        } else {
            for (int i = 0; i < userDto.getPhoneNumber().length(); i++) {
                if (userDto.getPhoneNumber().charAt(i) > 57 && userDto.getPhoneNumber().charAt(i) < 48){
                    return false;
                }
            }
        }
        if (userDto.getPassword().length() < 4){
            return false;
        } else return userDto.getPasswordRep().equals(userDto.getPassword());
    }

    @PostMapping("/reg")
    public String regNewUser(@ModelAttribute UserRegDto userRegDto, RedirectAttributes redirect) {
        if (!isValid(userRegDto)){
            redirect.addFlashAttribute("message", "Ошибка регистрации: неверно введенные данные!");
        } else {
            userService.saveUser(userRegDto);
            redirect.addFlashAttribute("message", "Вы прошли регистрацию!");
        }
        return "redirect:/";
    }
    
}
