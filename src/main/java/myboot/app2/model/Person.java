package myboot.app2.model;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email(message = "Email should be valid")
    private String email;

    @URL(message = "Website should be a valid URL")
    private String website;

    @NotNull
    @Past(message = "Birth date should be in the past")
    private Date birthDate;

    @NotEmpty
    private String password;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private CV cv;

}
