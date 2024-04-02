package webportal.libweb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import webportal.libweb.models.enums.Role;

@Data
@Table(name = "Users")
@Entity
@SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    
    @JsonIgnore
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String phoneNumber, String password, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

}
