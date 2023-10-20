package myboot.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Represents an individual activity that can be associated with a CV.
 * This might include professional experiences, education, leisure activities, or specific projects.
 *
 * @author Achraf
 * @version 1.0
 */
@Entity
@Data
public class Activity {

    /**
     * The unique identifier for the activity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * The start year of the activity.
     * This year should be in the past.
     */
    @NotNull
    @Column(nullable = false)
    @Max(message = "Start year should be in the past", value = 2023)
    private int startYear;

    /**
     * The end year of the activity.
     */
    @NotNull
    @Column(nullable = false)
    private int endYear;

    /**
     * The nature or type of the activity (e.g., Professional Experience, Education, etc.).
     */
    @NotNull
    @Column(nullable = false)
    private ActivityNature nature;

    /**
     * The title or name of the activity.
     * For example, it could be a job title or degree name.
     */
    @NotNull
    @Column(nullable = false)
    private String title; //Ex : Chef de projets informatique / Licence / Master...

    /**
     * A description providing additional details about the activity.
     */
    @Size(max = 100000)
    private String description;


    /**
     * The CV associated with this activity.
     * This association is bi-directional with a many-to-one mapping, where the Activity is not the owner.
     * This field is ignored when serializing to JSON format.
     */
    @ManyToOne
    @JsonIgnore
    private CV cv;

    /**
     * Validates that the start year of the activity is before its end year.
     *
     * @return true if the start year is before the end year, false otherwise.
     */
    @AssertTrue(message = "Start year should be before endYear")
    public boolean isStartYearBeforeEndYear() {
        return startYear < endYear;
    }
}
