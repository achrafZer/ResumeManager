package myboot.app2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Data
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(nullable = false)
    @Valid
    private Person person;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @Valid
    private List<Activity> activities;

}
