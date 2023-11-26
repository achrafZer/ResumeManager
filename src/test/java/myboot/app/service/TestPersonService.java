package myboot.app.service;

import myboot.app.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


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
    void testGetPersonsByFirstName() {
        personService.savePerson(testPerson);
        List<Person> personList = personService.getPersonsByFirstName("Jean");
        assertEquals(1, personList.size());
        assertEquals("SAMSON", personList.get(0).getLastName());
    }

    @Test
    void testGetPersonsByPartFirstName() {
        personService.savePerson(testPerson);
        List<Person> personList = personService.getPersonsByFirstName("Jea");
        assertEquals(1, personList.size());
        assertEquals("SAMSON", personList.get(0).getLastName());
    }

    @Test
    void testGetPersonsByPartOfActivityTitle() {
        personService.savePerson(testPerson);
        List<Person> personList = personService.getPersonsByPartOfActivityTitle("Chef de");
        assertEquals(1, personList.size());
        assertEquals("ZERHOUNI", personList.get(0).getLastName());
    }



    @Test
    void testGetPersonsByLastName() {
        personService.savePerson(testPerson);
        List<Person> personList = personService.getPersonsByLastName("SAMSON");
        assertEquals(1, personList.size());
        assertEquals("Jean", personList.get(0).getFirstName());
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
