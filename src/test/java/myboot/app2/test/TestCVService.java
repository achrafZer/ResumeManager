package myboot.app2.test;

import myboot.app2.model.CV;
import myboot.app2.model.Person;
import myboot.app2.service.CVService;
import myboot.app2.service.PersonService;
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
public class TestCVService {

    @Autowired
    private CVService cvService;

    private CV testCV;
    private Person testPerson;

    @Autowired
    private PersonService personService;



    @BeforeEach
    public void setUp() throws ParseException {
        testPerson = new Person();
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        testPerson.setEmail("johndoe@example.com");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1985-01-01");
        testPerson.setBirthDate(birthday);
        testPerson.setPassword("password123");
        personService.savePerson(testPerson);

        testCV = new CV();
        testCV.setPerson(testPerson);
    }

    @Test
    public void testSaveCV() {
        CV savedCV = cvService.saveCV(testCV);
        assertNotNull(savedCV.getId());
        // Test other assertions as per your needs.
    }

    @Test
    public void testUpdateCV() {
        CV savedCV = cvService.saveCV(testCV);

        // Modify your CV object as needed for testing updates.
        CV updatedCV = cvService.updateCV(savedCV.getId(), savedCV);

        // Add assertions to check the update.
    }

    @Test
    public void testDeleteCV() {
        CV savedCV = cvService.saveCV(testCV);
        cvService.deleteCVById(savedCV.getId());
        assertNull(cvService.getCVById(savedCV.getId()));
    }

    @AfterEach
    public void tearDown() {
        testCV = null;
    }
}
