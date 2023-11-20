package myboot.app.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a User in the context of authentification. It has three parameters which are the same as in the
 * Person entity
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XUser {

    /**
     * The user email
     */
    @Id
    String userName;

    /**
     * The user password
     */
    @Basic
    String password;

    /**
     * The roles the person has
     */
    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> roles;

}
