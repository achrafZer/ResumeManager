package myboot.app2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
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
}
