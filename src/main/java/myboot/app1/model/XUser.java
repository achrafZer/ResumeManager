package myboot.app1.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XUser {

	@Id
	String userName;

	@Basic
	String password;

	@ElementCollection(fetch = FetchType.EAGER)
	Set<String> roles;

}
