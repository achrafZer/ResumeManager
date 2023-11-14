package myboot.app.dto;

import java.awt.*;
import java.util.Date;
import java.util.Set;

public class RegistrationDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String website;

    private Date birthDate;

    private String password;

    Set<String> roles;
}
