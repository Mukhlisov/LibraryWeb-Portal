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
import webportal.libweb.models.enums.Role;

@Data
@Table(name = "Users")
@Entity
@SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;

    @Column(name = "firs_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    
    @JsonIgnore
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;

}
