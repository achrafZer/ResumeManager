package myboot.app2.test;

import myboot.app2.model.Person;
import myboot.app2.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TestPersonService {

    @Autowired
    private PersonService personService;

    private Person testPerson;

    @BeforeEach
    public void setUp() throws Exception {
        testPerson = new Person();
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        testPerson.setEmail("johndoe@example.com");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1985-01-01");
        testPerson.setBirthDate(birthday);
        testPerson.setPassword("password123");
    }

    @Test
    public void testSavePerson() {
        Person savedPerson = personService.savePerson(testPerson);
        assertNotNull(savedPerson.getId());
        assertEquals(testPerson.getFirstName(), savedPerson.getFirstName());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person savedPerson = personService.savePerson(testPerson);

        savedPerson.setFirstName("Jane");
        savedPerson.setLastName("Doe");
        Person updatedPerson = personService.updatePerson(savedPerson.getId(), savedPerson);

        assertEquals("Jane", updatedPerson.getFirstName());
        assertEquals("Doe", updatedPerson.getLastName());
    }

    @Test
    public void testDeletePerson() {
        Person savedPerson = personService.savePerson(testPerson);
        personService.deletePersonById(savedPerson.getId());
        assertNull(personService.getPersonById(savedPerson.getId()));
    }

}
