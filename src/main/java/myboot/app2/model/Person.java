package myboot.app2.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String website;
    private Date birthDate;
    private String password; // TODO Est-ce que le cryptage des MDP est pour cette partie là ou pour après

    @OneToOne
    private CV cv;

}
