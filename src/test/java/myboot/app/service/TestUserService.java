package myboot.app.service;

import myboot.app.dao.PersonRepository;
import myboot.app.dao.XUserRepository;
import myboot.app.dto.RegistrationDTO;
import myboot.app.model.Person;
import myboot.app.model.XUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class TestUserService {

    @Autowired
    private XUserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    RegistrationDTO registrationDTO;

    @BeforeEach
    public void setup() throws ParseException {
        registrationDTO = new RegistrationDTO();
        registrationDTO.setEmail("testUserService@example.com");
        registrationDTO.setPassword("password");
        registrationDTO.setFirstName("Test");
        registrationDTO.setLastName("User");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        registrationDTO.setBirthDate(birthday);

        registrationDTO.setRoles(new HashSet<>());
    }



    @Test
    public void testSignup() {
        String token = userService.signup(registrationDTO);

        assertNotNull(token);

        XUser user = userRepository.findById("testUserService@example.com").orElse(null);
        assertNotNull(user);
        assertTrue(entityManager.contains(user));
        assertEquals("testUserService@example.com", user.getUserName());
        assertTrue(passwordEncoder.matches("password", user.getPassword()));

        Person person = personRepository.findByEmail("testUserService@example.com");
        assertNotNull(person);
        assertTrue(entityManager.contains(person));
        assertEquals("Test", person.getFirstName());
        assertEquals("User", person.getLastName());
        assertEquals("testUserService@example.com", person.getEmail());
        assertTrue(passwordEncoder.matches("password", person.getPassword()));





    }
}
