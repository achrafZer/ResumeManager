package myboot.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

/**
 * Data Transfer Object (DTO) for handling registration information.
 * This class is used to transfer user registration data, such as personal information and desired roles.
 * It uses Lombok annotations to generate getter and setter methods automatically.
 */
@Getter
@Setter
public class RegistrationDTO {

    /**
     * Set of role names that the we assign to the user
     */
    Set<String> roles;

    /**
     * First name of the user registering.
     */
    private String firstName;

    /**
     * Last name of the user registering.
     */
    private String lastName;

    /**
     * Email address of the user registering. Wich is also his username
     */
    private String email;

    /**
     * Website URL of the user, if applicable.
     */
    private String website;

    /**
     * Date of birth of the user.
     */
    private Date birthDate;

    /**
     * Password chosen by the user for account creation. Of course the password isn't put directly into the database It
     * is encrypted first
     */
    private String password;
}
