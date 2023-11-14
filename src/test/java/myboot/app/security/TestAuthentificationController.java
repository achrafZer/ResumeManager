package myboot.app.security;

import myboot.app.dao.PersonRepository;
import myboot.app.dto.RegistrationDTO;
import myboot.app.model.XUser;
import myboot.app5.security.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAuthentificationController {

    @Autowired
    PersonRepository personRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/secu-users";
    }

    @Test
    public void testEmailSignup() throws ParseException {
        System.out.println(personRepository.findAll());

        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setFirstName("Achraf");
        registrationDTO.setLastName("ZERHOUNI");
        registrationDTO.setEmail("zerhouniachraf@hotmail.com");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        registrationDTO.setBirthDate(birthday);
        registrationDTO.setPassword("mon MDP");
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        registrationDTO.setRoles(roles);

        UserService userService = new UserService();
        userService.signup(registrationDTO);

    }

}
