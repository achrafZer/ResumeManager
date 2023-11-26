package myboot.app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import java.util.List;

/**
 * Represents a Curriculum Vitae (CV) entity in the application model.
 * A CV is associated with a specific person and contains a list of activities.
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
     * The person associated with this CV. The association is bi-directional
     * and is marked as non-nullable. This field is ignored in JSON serialization
     * to prevent circular references.
     */
    @OneToOne
    @JsonIgnore
    @JoinColumn(nullable = false)
    @Valid
    private Person person;

    /**
     * List of activities associated with this CV. This can include professional experiences,
     * educational history, projects, etc. The association is mapped by the 'cv' field
     * in the 'Activity' entity and includes cascade operations for all activities.
     */
    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @Valid
    private List<Activity> activities;

    // Note: Lombok's @Data annotation automatically generates getters, setters, equals, hashCode, and toString methods.
}
