package myboot.app1.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQuery(name = "findAllMovies", query = "SELECT m FROM Movie m")

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;

	@Basic
	@NotBlank(message = "{movie.name}")
	private String name;

	@Basic
	@Min(value = 1900, message = "{movie.year}")
	@Max(value = 2100, message = "{movie.year}")
	private int year;

	@Basic
	@Size(max = 200, message = "{movie.description}")
	private String description;

	public Movie(String name, int year, String description) {
		this(0, name, year, description);
	}

}
