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

    private int year;
    private String nature; // Consider using an Enum for nature types
    private String title;
    private String description;
    private String webAddress;

    @ManyToOne
    private CV cv;
}
