package webportal.libweb;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String passwordRep;
}
