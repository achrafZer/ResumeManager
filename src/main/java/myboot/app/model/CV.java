package myboot.app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Represents a Curriculum Vitae (CV) that is associated with a person
 * and contains a list of activities.
 *
 * @author Achraf
 * @version 1.0
 */
@Entity
@Data
public class CV {

    /**
     * The unique identifier for the CV.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The person associated with this CV. This association is bi-directional
     * with a one-to-one mapping, and the person is the owner of the relationship.
     * This field is ignored when serializing to JSON format.
     */
    @OneToOne
    @JsonIgnore
    @JoinColumn(nullable = false)
    @Valid
    private Person person;

    /**
     * List of activities that belong to this CV.
     * This association is bi-directional with a one-to-many mapping, where the CV is the owner.
     */
    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @Valid
    private List<Activity> activities;

}
