package myboot.app.test;

import myboot.app.model.CV;
import myboot.app.model.Person;
import myboot.app.service.CVService;
import myboot.app.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TestCVService {

    @Autowired
    private CVService cvService;

    private CV testCV;

    private Person testPerson2;

    private Person testPerson;

    @Autowired
    private PersonService personService;

    @BeforeEach
    public void setUp() throws ParseException {
        testPerson = new Person();
        testPerson.setFirstName("Jean");
        testPerson.setLastName("SAMSON");
        testPerson.setEmail("jeansamson@example.com");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1985-01-01");
        testPerson.setBirthDate(birthday);
        testPerson.setPassword("password123");
        personService.savePerson(testPerson);

        testPerson2 = new Person();
        testPerson2.setFirstName("Person");
        testPerson2.setLastName("LastName");
        testPerson2.setEmail("personlastname@example.com");
        Date birthday1 = sdf.parse("1985-01-01");
        testPerson2.setBirthDate(birthday1);
        testPerson2.setPassword("password123");
        personService.savePerson(testPerson2);

        testCV = new CV();
        testCV.setPerson(testPerson);
    }

    @Test
    void testSaveCV() {
        CV savedCV = cvService.saveCV(testCV);
        assertNotNull(savedCV.getId());
    }

    @Test
    void testGetAllCVs() {
        cvService.saveCV(testCV);
        assertEquals("Jean", cvService.getAllCVs().get(1).getPerson().getFirstName());
    }

    @Test
    void testUpdateCV() {
        CV savedCV = cvService.saveCV(testCV);
        savedCV.setPerson(testPerson2);
        CV updatedCV = cvService.updateCV(savedCV.getId(), savedCV);

        assertEquals("Person", cvService.getCVById(savedCV.getId()).getPerson().getFirstName());
    }

    @Test
    void testDeleteCV() {
        CV savedCV = cvService.saveCV(testCV);
        cvService.deleteCVById(savedCV.getId());
        assertNull(cvService.getCVById(savedCV.getId()));
    }

    @AfterEach
    public void tearDown() {
        testCV = null;
        personService.deletePersonById(testPerson2.getId());
        personService.deletePersonById(testPerson.getId());

    }
}
