package myboot.app.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TestPersonController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreatePerson() { //POST "/persons"
        //TODO
//        String movieDtoJson = "{ \"name\": \"Test Movie\", \"year\": 2000, \"description\": \"Test Description\" }";
//
//        mockMvc.perform(post("/api/movies")
//                        .contentType("application/json")
//                        .content(movieDtoJson))
//                .andExpect(status().isOk());
    }

    @Test
    void testGetPersons() throws Exception {
        mockMvc.perform(get("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"firstName\": \"Achraf\",\n" +
                        "    \"lastName\": \"ZERHOUNI\",\n" +
                        "    \"email\": \"zerhouniachraf@hotmail.com\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-12-24T23:00:00.000+00:00\",\n" +
                        "    \"password\": \"AchrafPassword\",\n" +
                        "    \"cv\": {\n" +
                        "      \"id\": 1,\n" +
                        "      \"activities\": [\n" +
                        "        {\n" +
                        "          \"id\": 1,\n" +
                        "          \"startYear\": 2023,\n" +
                        "          \"endYear\": 2024,\n" +
                        "          \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "          \"title\": \"Chef de projets informatiques\",\n" +
                        "          \"description\": \"En étant chef de projets informatiques à la Métropole, je m'occupe de préparer les environnement de travail pour mon équipe de développement, de garantir le bon déroulement de la méthode AGILE, ainsi que d'anticiper les moments où je dois intervenir dans le code\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 2,\n" +
                        "          \"startYear\": 2021,\n" +
                        "          \"endYear\": 2023,\n" +
                        "          \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "          \"title\": \"Equipier polyvalent\",\n" +
                        "          \"description\": \"En étant équipier plyvalent en restauration, je m'occupais de différents posts en fonction du beoin. Des fois en cuisine, des fois à la caisse et des fois je devais uniquement assister les clients durant leurs commandes\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 3,\n" +
                        "          \"startYear\": 2019,\n" +
                        "          \"endYear\": 2022,\n" +
                        "          \"nature\": \"EDUCATION\",\n" +
                        "          \"title\": \"Licence, Informatique\",\n" +
                        "          \"description\": \"Durant ma licence, j'ai appris les éléments suivants : Intelligence artificielle, appliquée sur un jeu d'échecs · Java: Création d'un jeu d'échecs · Programmation orientée objet (POO)\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 4,\n" +
                        "          \"startYear\": 2022,\n" +
                        "          \"endYear\": 2024,\n" +
                        "          \"nature\": \"EDUCATION\",\n" +
                        "          \"title\": \"Master, Informatique\",\n" +
                        "          \"description\": \"Durant mon master, j'ai appris les éléments suivants : Framework Spring · Enterprise JavaBeans · Threads · Fiabilité logicielle à l'aide de Gradle en java \"\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"firstName\": \"Heba\",\n" +
                        "    \"lastName\": \"ABU RABIA\",\n" +
                        "    \"email\": \"heba.abu-rabia@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-08-21T22:00:00.000+00:00\",\n" +
                        "    \"password\": \"HebaPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 3,\n" +
                        "    \"firstName\": \"Walid\",\n" +
                        "    \"lastName\": \"ADDOUCHE\",\n" +
                        "    \"email\": \"walid.addouche@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-03-03T23:00:00.000+00:00\",\n" +
                        "    \"password\": \"WalidPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 4,\n" +
                        "    \"firstName\": \"Rafik\",\n" +
                        "    \"lastName\": \"CHAIB\",\n" +
                        "    \"email\": \"rafik.chaib@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"1999-02-03T23:00:00.000+00:00\",\n" +
                        "    \"password\": \"RafikPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 5,\n" +
                        "    \"firstName\": \"Maxime\",\n" +
                        "    \"lastName\": \"GUILIANI\",\n" +
                        "    \"email\": \"maxime.guiliani@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"1999-07-03T22:00:00.000+00:00\",\n" +
                        "    \"password\": \"MaximePassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 6,\n" +
                        "    \"firstName\": \"Diego\",\n" +
                        "    \"lastName\": \"IMBERT\",\n" +
                        "    \"email\": \"diego.imbert@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2001-08-03T22:00:00.000+00:00\",\n" +
                        "    \"password\": \"DiegoPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 7,\n" +
                        "    \"firstName\": \"Kpotivi\",\n" +
                        "    \"lastName\": \"KPOTY\",\n" +
                        "    \"email\": \"kpotivi.kpoty@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"1997-01-03T23:00:00.000+00:00\",\n" +
                        "    \"password\": \"KpotiviPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 8,\n" +
                        "    \"firstName\": \"Elie\",\n" +
                        "    \"lastName\": \"NICOLAS\",\n" +
                        "    \"email\": \"elie.nicolas@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"1996-05-03T22:00:00.000+00:00\",\n" +
                        "    \"password\": \"EliePassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 9,\n" +
                        "    \"firstName\": \"Juba\",\n" +
                        "    \"lastName\": \"OUARAB\",\n" +
                        "    \"email\": \"juba.ouarab@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-04-03T22:00:00.000+00:00\",\n" +
                        "    \"password\": \"JubaPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 10,\n" +
                        "    \"firstName\": \"Manal\",\n" +
                        "    \"lastName\": \"STIHI\",\n" +
                        "    \"email\": \"manal.stihi@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-06-03T22:00:00.000+00:00\",\n" +
                        "    \"password\": \"ManalPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  }\n" +
                        "]"));

    }

    @Test
    void testGetPersonById() throws Exception {
        mockMvc.perform(get("/api/persons/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"firstName\": \"Heba\",\n" +
                        "  \"lastName\": \"ABU RABIA\",\n" +
                        "  \"email\": \"heba.abu-rabia@univ-amu.fr\",\n" +
                        "  \"website\": null,\n" +
                        "  \"birthDate\": \"2000-08-21T22:00:00.000+00:00\",\n" +
                        "  \"password\": \"HebaPassword\",\n" +
                        "  \"cv\": null\n" +
                        "}")
                );
    }

    @Test
    void testGetPersonsByFirstName() throws Exception { // GET "persons/search-first-name"
        mockMvc.perform(get("/api/persons/search-first-name?firstName=ba")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"firstName\": \"Heba\",\n" +
                        "    \"lastName\": \"ABU RABIA\",\n" +
                        "    \"email\": \"heba.abu-rabia@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-08-21T22:00:00.000+00:00\",\n" +
                        "    \"password\": \"HebaPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 9,\n" +
                        "    \"firstName\": \"Juba\",\n" +
                        "    \"lastName\": \"OUARAB\",\n" +
                        "    \"email\": \"juba.ouarab@univ-amu.fr\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-04-03T22:00:00.000+00:00\",\n" +
                        "    \"password\": \"JubaPassword\",\n" +
                        "    \"cv\": null\n" +
                        "  }\n" +
                        "]")
                );
    }

    @Test
    void testGetPersonsByLastName() throws Exception { // GET "persons/search-last-name"
        mockMvc.perform(get("/api/persons/search-last-name?lastName=houni")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"firstName\": \"Achraf\",\n" +
                        "    \"lastName\": \"ZERHOUNI\",\n" +
                        "    \"email\": \"zerhouniachraf@hotmail.com\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-12-24T23:00:00.000+00:00\",\n" +
                        "    \"password\": \"AchrafPassword\",\n" +
                        "    \"cv\": {\n" +
                        "      \"id\": 1,\n" +
                        "      \"activities\": [\n" +
                        "        {\n" +
                        "          \"id\": 1,\n" +
                        "          \"startYear\": 2023,\n" +
                        "          \"endYear\": 2024,\n" +
                        "          \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "          \"title\": \"Chef de projets informatiques\",\n" +
                        "          \"description\": \"En étant chef de projets informatiques à la Métropole, je m'occupe de préparer les environnement de travail pour mon équipe de développement, de garantir le bon déroulement de la méthode AGILE, ainsi que d'anticiper les moments où je dois intervenir dans le code\",\n" +
                        "          \"startYearBeforeEndYear\": true\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 2,\n" +
                        "          \"startYear\": 2021,\n" +
                        "          \"endYear\": 2023,\n" +
                        "          \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "          \"title\": \"Equipier polyvalent\",\n" +
                        "          \"description\": \"En étant équipier plyvalent en restauration, je m'occupais de différents posts en fonction du beoin. Des fois en cuisine, des fois à la caisse et des fois je devais uniquement assister les clients durant leurs commandes\",\n" +
                        "          \"startYearBeforeEndYear\": true\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 3,\n" +
                        "          \"startYear\": 2019,\n" +
                        "          \"endYear\": 2022,\n" +
                        "          \"nature\": \"EDUCATION\",\n" +
                        "          \"title\": \"Licence, Informatique\",\n" +
                        "          \"description\": \"Durant ma licence, j'ai appris les éléments suivants : Intelligence artificielle, appliquée sur un jeu d'échecs · Java: Création d'un jeu d'échecs · Programmation orientée objet (POO)\",\n" +
                        "          \"startYearBeforeEndYear\": true\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 4,\n" +
                        "          \"startYear\": 2022,\n" +
                        "          \"endYear\": 2024,\n" +
                        "          \"nature\": \"EDUCATION\",\n" +
                        "          \"title\": \"Master, Informatique\",\n" +
                        "          \"description\": \"Durant mon master, j'ai appris les éléments suivants : Framework Spring · Enterprise JavaBeans · Threads · Fiabilité logicielle à l'aide de Gradle en java \",\n" +
                        "          \"startYearBeforeEndYear\": true\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  }\n" +
                        "]")
                );

    }

    @Test
    void testGetPersonsByPartOfActivityTitle() throws Exception { // GET "persons/search-by-activity-title"
        mockMvc.perform(get("/api/persons/search-by-activity-title?activityTitle=Chef de")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"firstName\": \"Achraf\",\n" +
                        "    \"lastName\": \"ZERHOUNI\",\n" +
                        "    \"email\": \"zerhouniachraf@hotmail.com\",\n" +
                        "    \"website\": null,\n" +
                        "    \"birthDate\": \"2000-12-24T23:00:00.000+00:00\",\n" +
                        "    \"password\": \"AchrafPassword\",\n" +
                        "    \"cv\": {\n" +
                        "      \"id\": 1,\n" +
                        "      \"activities\": [\n" +
                        "        {\n" +
                        "          \"id\": 1,\n" +
                        "          \"startYear\": 2023,\n" +
                        "          \"endYear\": 2024,\n" +
                        "          \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "          \"title\": \"Chef de projets informatiques\",\n" +
                        "          \"description\": \"En étant chef de projets informatiques à la Métropole, je m'occupe de préparer les environnement de travail pour mon équipe de développement, de garantir le bon déroulement de la méthode AGILE, ainsi que d'anticiper les moments où je dois intervenir dans le code\",\n" +
                        "          \"startYearBeforeEndYear\": true\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 2,\n" +
                        "          \"startYear\": 2021,\n" +
                        "          \"endYear\": 2023,\n" +
                        "          \"nature\": \"PROFESSIONAL_EXPERIENCE\",\n" +
                        "          \"title\": \"Equipier polyvalent\",\n" +
                        "          \"description\": \"En étant équipier plyvalent en restauration, je m'occupais de différents posts en fonction du beoin. Des fois en cuisine, des fois à la caisse et des fois je devais uniquement assister les clients durant leurs commandes\",\n" +
                        "          \"startYearBeforeEndYear\": true\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 3,\n" +
                        "          \"startYear\": 2019,\n" +
                        "          \"endYear\": 2022,\n" +
                        "          \"nature\": \"EDUCATION\",\n" +
                        "          \"title\": \"Licence, Informatique\",\n" +
                        "          \"description\": \"Durant ma licence, j'ai appris les éléments suivants : Intelligence artificielle, appliquée sur un jeu d'échecs · Java: Création d'un jeu d'échecs · Programmation orientée objet (POO)\",\n" +
                        "          \"startYearBeforeEndYear\": true\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"id\": 4,\n" +
                        "          \"startYear\": 2022,\n" +
                        "          \"endYear\": 2024,\n" +
                        "          \"nature\": \"EDUCATION\",\n" +
                        "          \"title\": \"Master, Informatique\",\n" +
                        "          \"description\": \"Durant mon master, j'ai appris les éléments suivants : Framework Spring · Enterprise JavaBeans · Threads · Fiabilité logicielle à l'aide de Gradle en java \",\n" +
                        "          \"startYearBeforeEndYear\": true\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  }\n" +
                        "]")
                );
    }

    @Test
    void testGetUnvalidPersonById() throws Exception { //GET "persons/{id}"
        mockMvc.perform(get("/api/persons/1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testUpdatePerson() {//PUT "persons/{id}"
        //TODO

    }

    @Test
    void testUpdateUnvalidPerson() {//PUT "persons/{id}"
        //TODO

    }

    @Test
    void testDeletePerson() {//DELETE "persons/{id}"
        //TODO

    }

    @Test
    void testDeleteUnvalidPerson() {//DELETE "persons/{id}"
        //TODO

    }

    @Test
    void testTriggerException() { // GET "exception-test")
        //TODO

    }



}
