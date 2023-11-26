package myboot.app.security;

import myboot.app.dao.PersonRepository;
import myboot.app.dao.XUserRepository;
import myboot.app.dto.RegistrationDTO;
import myboot.app.model.Person;
import myboot.app.model.XUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TestAuthentificationController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private XUserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    private XUser user;

    private Person person;

    @AfterEach
    public void cleanup() {
        userRepository.deleteById(user.getUserName());
        personRepository.delete(person);
    }



    @Test
    public void testSignup() throws Exception {
        String baseUrl = "http://localhost:" + port + "/secu-users/signup";
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setPassword("password");
        registrationDTO.setFirstName("Test");
        registrationDTO.setLastName("User");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        registrationDTO.setBirthDate(birthday);

        registrationDTO.setRoles(new HashSet<>(Collections.singletonList("USER")));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegistrationDTO> request = new HttpEntity<>(registrationDTO, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals("User registered successfully", response.getBody());


        user = userRepository.findById("test@example.com").orElse(null);
        assertNotNull(user);
        assertTrue(entityManager.contains(user));
        assertEquals("test@example.com", user.getUserName());
        assertTrue(passwordEncoder.matches("password", user.getPassword()));

        person = personRepository.findByEmail("test@example.com");
        assertNotNull(person);
        assertTrue(entityManager.contains(person));
        assertEquals("Test", person.getFirstName());
        assertEquals("User", person.getLastName());
        assertEquals("test@example.com", person.getEmail());
        assertTrue(passwordEncoder.matches("password", person.getPassword()));

    }
}