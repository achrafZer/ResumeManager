package myboot.app.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestActivityController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateActivity() throws Exception {
        //todo
    }
    @Test
    void testGetAllActivities() throws Exception { //GET "activities"
        mockMvc.perform(get("/api/activities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"startYear\": 2023,\n" +
                        "    \"endYear\": 2024,\n" +
                        "    \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "    \"title\": \"Chef de projets informatiques\",\n" +
                        "    \"description\": \"En étant chef de projets informatiques à la Métropole, je m'occupe de préparer les environnement de travail pour mon équipe de développement, de garantir le bon déroulement de la méthode AGILE, ainsi que d'anticiper les moments où je dois intervenir dans le code\",\n" +
                        "    \"startYearBeforeEndYear\": true\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"startYear\": 2021,\n" +
                        "    \"endYear\": 2023,\n" +
                        "    \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "    \"title\": \"Equipier polyvalent\",\n" +
                        "    \"description\": \"En étant équipier plyvalent en restauration, je m'occupais de différents posts en fonction du beoin. Des fois en cuisine, des fois à la caisse et des fois je devais uniquement assister les clients durant leurs commandes\",\n" +
                        "    \"startYearBeforeEndYear\": true\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 3,\n" +
                        "    \"startYear\": 2019,\n" +
                        "    \"endYear\": 2022,\n" +
                        "    \"nature\": \"EDUCATION\",\n" +
                        "    \"title\": \"Licence, Informatique\",\n" +
                        "    \"description\": \"Durant ma licence, j'ai appris les éléments suivants : Intelligence artificielle, appliquée sur un jeu d'échecs · Java: Création d'un jeu d'échecs · Programmation orientée objet (POO)\",\n" +
                        "    \"startYearBeforeEndYear\": true\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 4,\n" +
                        "    \"startYear\": 2022,\n" +
                        "    \"endYear\": 2024,\n" +
                        "    \"nature\": \"EDUCATION\",\n" +
                        "    \"title\": \"Master, Informatique\",\n" +
                        "    \"description\": \"Durant mon master, j'ai appris les éléments suivants : Framework Spring · Enterprise JavaBeans · Threads · Fiabilité logicielle à l'aide de Gradle en java \",\n" +
                        "    \"startYearBeforeEndYear\": true\n" +
                        "  }\n" +
                        "]"));

    }

    @Test
    void testGetActivityById() throws Exception { //GET "activities/{id}"
        mockMvc.perform(get("/api/activities/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"startYear\": 2023,\n" +
                        "  \"endYear\": 2024,\n" +
                        "  \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "  \"title\": \"Chef de projets informatiques\",\n" +
                        "  \"description\": \"En étant chef de projets informatiques à la Métropole, je m'occupe de préparer les environnement de travail pour mon équipe de développement, de garantir le bon déroulement de la méthode AGILE, ainsi que d'anticiper les moments où je dois intervenir dans le code\",\n" +
                        "  \"startYearBeforeEndYear\": true\n" +
                        "}")
                );
    }
    @Test
    void testGetUnvalidActivityById() throws Exception { //GET "persons/{unvalidId}"
        mockMvc.perform(get("/api/activities/100000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testUpdatePerson() {//PUT "activities/{id}"
        //TODO
    }

    @Test
    void testUpdateUnvalidPerson() {//PUT "activities/{unvalidId}"
        //TODO
    }

    @Test
    void testDeletePerson() {//DELETE "activities/{id}"
        //TODO
    }

    @Test
    void testDeleteUnvalidPerson() {//DELETE "activities/{unvalisId}"
        //TODO
    }
}
