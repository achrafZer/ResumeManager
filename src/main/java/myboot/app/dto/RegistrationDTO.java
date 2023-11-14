package myboot.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class RegistrationDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String website;

    private Date birthDate;

    private String password;

    Set<String> roles;
}
