package myboot.app1.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import myboot.app1.dao.MovieRepository;
import myboot.app1.model.Movie;

@SpringBootTest
public class TestMovieRepository {

	@Autowired
	MovieRepository r;

	@Test
	public void testSave() {
		var m = r.save(new Movie("Test 1", 2000, "Desc"));
		var m2 = r.findById(m.getId());
		assertEquals(m2.get().getName(), "Test 1");
	}

}
