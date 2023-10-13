package myboot.app2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private Person person;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    private List<Activity> activities;
}
