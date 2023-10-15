package myboot.app2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Max(message = "Start year should be in the past", value = 2023)
    private int startYear;

    @NotNull
    @Column(nullable = false)
    private int endYear;

    @NotNull
    @Column(nullable = false)
    private ActivityNature nature; //Ex : Expérience pro / Formation...

    @NotNull
    @Column(nullable = false)
    private String title; //Ex : Chef de projets informatique / Licence / Master...

    @Size(max = 100000)
    private String description;

    @ManyToOne
    @JsonIgnore
    private CV cv;

    @AssertTrue(message = "Start year should be before endYear")
    public boolean isStartYearBeforeEndYear() {
        return startYear < endYear;
    }
}
