package myboot.app2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int startYear;
    private int endYear;

    private ActivityNature nature; //Expérience pro / Formation...
    private String title; //Ex : Chef de projets informatique / Licence / Master...

    @Size(max = 100000)
    private String description;

    @ManyToOne
    @JsonIgnore
    private CV cv;
}
