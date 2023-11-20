package myboot.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents an individual activity, such as a professional experience, educational course, or a project.
 * This entity is part of the application's model and is used to fill a resume
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
     * This year should be in the past and is validated as such.
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
     * The nature or type of the activity, such as a professional experience or education.
     */
    @NotNull
    @Column(nullable = false)
    private ActivityNature nature; //Ex : Expérience pro / Formation...

    /**
     * The title of the activity, which can be a job title, degree name, or project name.
     */
    @NotNull
    @Column(nullable = false)
    private String title; //Ex : Chef de projets informatique / Licence / Master...

    /**
     * A detailed description of the activity.
     * The description size is limited to 100000 characters.
     */
    @Size(max = 100000)
    private String description;

    /**
     * The CV associated with this activity.
     */
    @ManyToOne
    @JsonIgnore
    private CV cv;

    /**
     * Validates that the start year of the activity is before the end year.
     *
     * @return true if the start year is earlier than the end year, false otherwise.
     */
    @AssertTrue(message = "Start year should be before endYear")
    public boolean isStartYearBeforeEndYear() {
        return startYear < endYear;
    }
}
