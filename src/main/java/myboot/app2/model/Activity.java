package myboot.app2.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private String description;

    @ManyToOne
    private CV cv;
}
