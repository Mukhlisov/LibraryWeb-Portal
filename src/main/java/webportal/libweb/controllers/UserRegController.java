package webportal.libweb.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import lombok.AllArgsConstructor;
import webportal.libweb.UserRegDto;
import webportal.libweb.User.User;
import webportal.libweb.User.UserService;
import webportal.libweb.enums.Role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
@RequestMapping("/reg")
@AllArgsConstructor
public class UserRegController {

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
        if (text.length() == 0){
            return false;
        } else if (!containsOnlyLetters(text)){
            return false;
        }
        return true;
    }

    private static Boolean isValid(UserRegDto userDto){
        Boolean message = validateTextField(userDto.getFirstName());
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
        } else if (!userDto.getPasswordRep().equals(userDto.getPassword())){
            return false;
        }
        return true;
    }

    @PostMapping
    public String regNewUser(@ModelAttribute UserRegDto userRegDto, RedirectAttributes redirect) {
        if (!isValid(userRegDto)){
            redirect.addFlashAttribute("message", "Ошибка регистрации: неверно введенные данные!");
        } else {
            redirect.addFlashAttribute("message", "Вы прошли регистрацию!");
            User user = new User(userRegDto.getFirstName(), userRegDto.getLastName(), 
                                userRegDto.getPhoneNumber(), userRegDto.getEmail(), 
                                userRegDto.getPassword(), Role.OWNER);
            userService.saveUser(user);
        }
        return "redirect:/";
    }
    
}
