package myboot.app2.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;
    private String website;

    @NotNull
    private Date birthDate;

    @NotEmpty
    private String password;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private CV cv;

}
