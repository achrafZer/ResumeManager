package myboot.app1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import myboot.app1.model.Movie;

@Repository
@Transactional
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	List<Movie> findByName(String name);

	List<Movie> findByNameLike(String name);

}