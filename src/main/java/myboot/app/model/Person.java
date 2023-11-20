package myboot.app.model;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * Represents a person with various personal details.
 * This entity is the main part of the project
 */
@Entity
@Data
public class Person {

    /**
     * The unique identifier for the person.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * First name of the person. We do a little check and validation
     */
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z-' ]+$", message = "First name can only contain alphabets, spaces, apostrophes, and hyphens")
    private String firstName;

    /**
     * Last name of the person. We do a little check and validation
     */
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z-' ]+$", message = "Last name can only contain alphabets, spaces, apostrophes, and hyphens")
    private String lastName;

    /**
     * The email that is also the username
     */
    @NotEmpty
    @Email(message = "Email should be valid")
    private String email;

    /**
     * Website URL of the person, if any. It should be in a valid URL format.
     */
    @URL(message = "Website should be a valid URL")
    private String website;

    /**
     * Birth date of the person. It must be a date in the past.
     */
    @NotNull
    @Past(message = "Birth date should be in the past")
    private Date birthDate;

    /**
     * Password for the person's account. This field is required.
     */
    @NotEmpty
    private String password;

    /**
     * The resume associated to the person
     */
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private CV cv;

}
