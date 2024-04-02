package webportal.libweb.dataTransferObj;

import lombok.Data;
import webportal.libweb.models.enums.Role;

@Data
public class UserRegDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber; 
    private String password;
    private Role role;
}
