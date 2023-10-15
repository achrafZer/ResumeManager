package myboot.app2.model;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z-' ]+$", message = "First name can only contain alphabets, spaces, apostrophes, and hyphens")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z-' ]+$", message = "Last name can only contain alphabets, spaces, apostrophes, and hyphens")
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
