package myboot.app.model;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * Represents a person with attributes such as name, email, website, and birth date.
 * Includes validation rules for the attributes.
 *
 * @author Achraf
 * @version 1.0
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
     * First name of the person. It must not be empty and can only contain alphabets, spaces,
     * apostrophes, and hyphens.
     */
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z-' ]+$", message = "First name can only contain alphabets, spaces, apostrophes, and hyphens")
    private String firstName;

    /**
     * Last name of the person. It must not be empty and can only contain alphabets, spaces,
     * apostrophes, and hyphens.
     */
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z-' ]+$", message = "Last name can only contain alphabets, spaces, apostrophes, and hyphens")
    private String lastName;

    /**
     * Email address of the person. It must be in a valid format.
     */
    @NotEmpty
    @Email(message = "Email should be valid")
    private String email;

    /**
     * Website URL of the person. It must be in a valid URL format.
     */
    @URL(message = "Website should be a valid URL")
    private String website;

    /**
     * Date of birth of the person. It must be a past date.
     */
    @NotNull
    @Past(message = "Birth date should be in the past")
    private Date birthDate;

    /**
     * Password for the person's account.
     */
    @NotEmpty
    private String password;

    /**
     * CV associated with the person.
     */
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private CV cv;

}
