package myboot.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
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
    @NotEmpty(message = "Le prénom d'une personne ne peut pas être vide")
    @Pattern(regexp = "^[a-zA-Z-' ]+$", message = "Le prénom peut uniquement contenir des lettres ou les symboles suivants: '-', ' '")
    private String firstName;

    /**
     * Last name of the person. We do a little check and validation
     */
    @NotEmpty(message = "Le nom d'une personne ne peut pas être vide")
    @Pattern(regexp = "^[a-zA-Z-' ]+$", message = "Le nom peut uniquement contenir des lettres ou les symboles suivants: '-', ' '")
    private String lastName;

    /**
     * The email that is also the username
     */
    @NotEmpty(message = "L'adresse mail' d'une personne ne peut pas être vide")
    @Email(message = "L'adresse mail doit avoir le format conventionnel d'une adresse mail")
    private String email;

    /**
     * Website URL of the person, if any. It should be in a valid URL format.
     */
    @Pattern(regexp = "^(www\\.http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$", message = "L'URL du site doit être valide")
    private String website;

    /**
     * Birth date of the person. It must be a date in the past.
     */
    @NotNull(message = "La date de naissance ne peut pas être nulle")
    @Past(message = "La date de naissance doit être dans le passé")
    private Date birthDate;

    /**
     * Password for the person's account. This field is required.
     */
    @NotEmpty(message = "Le mot-de-passe d'une personne ne peut pas être vide")
    private String password;

    /**
     * The resume associated to the person
     */
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private CV cv;

}
