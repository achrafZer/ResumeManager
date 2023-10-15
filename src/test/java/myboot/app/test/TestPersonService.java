package myboot.app.test;

import myboot.app.model.Person;
import myboot.app.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TestPersonService {

    @Autowired
    private PersonService personService;

    private Person testPerson;

    @BeforeEach
    public void setUp() throws Exception {
        testPerson = new Person();
        testPerson.setFirstName("Jean");
        testPerson.setLastName("SAMSON");
        testPerson.setEmail("jeansamson@example.com");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1985-01-01");
        testPerson.setBirthDate(birthday);
        testPerson.setPassword("password123");
    }

    @Test
    void testSavePerson() {
        Person savedPerson = personService.savePerson(testPerson);
        assertNotNull(savedPerson.getId());
        assertEquals(testPerson.getFirstName(), savedPerson.getFirstName());
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person savedPerson = personService.savePerson(testPerson);

        savedPerson.setFirstName("Achraf");
        savedPerson.setLastName("ZER");
        Person updatedPerson = personService.updatePerson(savedPerson.getId(), savedPerson);

        assertEquals("Achraf", updatedPerson.getFirstName());
        assertEquals("ZER", updatedPerson.getLastName());
    }

    @Test
    void testDeletePerson() {
        Person savedPerson = personService.savePerson(testPerson);
        personService.deletePersonById(savedPerson.getId());
        assertNull(personService.getPersonById(savedPerson.getId()));
    }

}
