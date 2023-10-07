package myboot.app1.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllers {

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(username = "aaa", roles = { "ADMIN" })
	public void testMovieList() throws Exception {
		mvc.perform(get("/movies"))//
				// afficher
				.andDo(print())//
				// vérifier le statut
				.andExpect(status().isOk())//
				// vérifier le nom de la vue
				.andExpect(view().name("movies"))//
				// vérifier le modèle
				.andExpect(model().attributeExists("movies"));
	}

	@Test
	@WithMockUser(username = "aaa", roles = { "ADMIN" })
	public void testRoot() throws Exception {
		mvc.perform(get("/"))//
				// vérifier la redirection vers /movies
				.andExpect(status().is3xxRedirection())//
				.andExpect(header().string("Location", "/movies"))//
				.andExpect(content().string(""));
	}

}
