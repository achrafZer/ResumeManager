package myboot.app.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for handling login information.
 * This class is used to transfer login data, such as username and password.
 * It uses Lombok annotations to generate getter and setter methods automatically.
 */
@Getter
@Setter
public class LoginDTO {

    /**
     * The email adress of the user attempting to log in.
     */
    private String username;

    /**
     * The password of the user attempting to log in.
     */
    private String password;

}

