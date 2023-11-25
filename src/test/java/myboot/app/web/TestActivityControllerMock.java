package myboot.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import myboot.app.dao.ActivityRepository;
import myboot.app.model.Activity;
import myboot.app.model.ActivityNature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class TestActivityControllerMock {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityRepository activityRepository;

    private Activity activity1;
    private Activity activity2;

    @BeforeEach
    public void setup() {
        activity1 = new Activity();
        activity1.setId(1L);
        activity1.setStartYear(2022);
        activity1.setEndYear(2024);
        activity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        activity1.setTitle("Développeur WEB");

        activity2 = new Activity();
        activity2.setId(2L);
        activity2.setStartYear(2001);
        activity2.setEndYear(2002);
        activity2.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        activity2.setTitle("Développeur JAVA");

    }

    @Test
    public void testCreateActivity() throws Exception {
        when(activityRepository.save(activity1)).thenReturn(activity1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(activity1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testGetAllActivities() throws Exception {
        when(activityRepository.findAll()).thenReturn(Arrays.asList(activity1, activity2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/activities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    public void testGetActivityByIdFound() throws Exception {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/activities/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testGetActivityByIdNotFound() throws Exception {
        when(activityRepository.findById(3L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/activities/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetActivityByTitle() throws Exception {

        when(activityRepository.findActivityByTitle("JAVA")).thenReturn(Arrays.asList(activity2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/activities/search-by-activity-title")
                        .param("activityTitle", "JAVA"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(2L))
                .andExpect(jsonPath("$[0].title").value("Développeur JAVA"));
    }

    @Test
    public void testUpdateActivity() throws Exception {
        // Configurer le comportement simulé du repository
        when(activityRepository.findById(any(Long.class))).thenReturn(Optional.of(activity1));
        when(activityRepository.save(any(Activity.class))).thenReturn(activity1);

        // Créer un ObjectMapper pour convertir les objets en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String updatedActivityJson = objectMapper.writeValueAsString(activity1);

        // Exécuter la requête PUT et vérifier la réponse
        mockMvc.perform(put("/api/activities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedActivityJson))
                .andExpect(status().isOk())
                .andExpect(content().json(updatedActivityJson));
    }

    @Test
    public void testDeleteActivity() throws Exception {
        when(activityRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/activities/1"))
                .andExpect(status().isNoContent());
    }
}
