package myboot.app2.test;

import myboot.app2.dao.ActivityRepository;
import myboot.app2.dao.CVRepository;
import myboot.app2.dao.PersonRepository;
import myboot.app2.model.Activity;
import myboot.app2.model.CV;
import myboot.app2.model.Person;
import myboot.app2.service.ActivityService;
import myboot.app2.service.CVService;
import myboot.app2.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServicesTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CVService cvService;

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRepository activityRepository;


    @Test
    public void testSavePerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        Person savedPerson = personService.savePerson(person);

        assertNotNull(savedPerson.getId());
        assertEquals("John", savedPerson.getFirstName());
        assertEquals("Doe", savedPerson.getLastName());

        Person retrievedPerson = personRepository.findById(savedPerson.getId()).orElse(null);
        assertNotNull(retrievedPerson);
        assertEquals(savedPerson.getId(), retrievedPerson.getId());
    }

    @Test
    public void testGetPersonById() {
        Person person = new Person();
        person.setFirstName("Jane");
        person.setLastName("Smith");

        person = personRepository.save(person);

        Long id = person.getId();

        Person retrievedPerson = personService.getPersonById(id);

        assertNotNull(retrievedPerson);
        assertEquals(id, retrievedPerson.getId());
        assertEquals("Jane", retrievedPerson.getFirstName());
        assertEquals("Smith", retrievedPerson.getLastName());
    }

    @Test
    public void testDeletePersonById() {
        Person person = new Person();
        person.setFirstName("Alice");
        person.setLastName("Johnson");

        person = personRepository.save(person);

        Long id = person.getId();

        personService.deletePersonById(id);

        Person deletedPerson = personRepository.findById(id).orElse(null);
        assertNull(deletedPerson);
    }

    @Test
    public void testSaveCV() {
        CV cv = new CV();

        CV savedCV = cvService.saveCV(cv);

        assertNotNull(savedCV.getId());
    }

    @Test
    public void testGetCVById() {
        CV cv = new CV();

        cv = cvRepository.save(cv);

        Long id = cv.getId();

        CV retrievedCV = cvService.getCVById(id);

        assertNotNull(retrievedCV);
        assertEquals(id, retrievedCV.getId());
    }

    @Test
    public void testDeleteCVById() {
        CV cv = new CV();

        cv = cvRepository.save(cv);

        Long id = cv.getId();

        cvService.deleteCVById(id);

        CV deletedCV = cvRepository.findById(id).orElse(null);
        assertNull(deletedCV);
    }

    @Test
    public void testSaveActivity() {
        Activity activity = new Activity();

        Activity savedActivity = activityService.saveActivity(activity);

        assertNotNull(savedActivity.getId());
    }

    @Test
    public void testGetActivityById() {
        Activity activity = new Activity();

        activity = activityRepository.save(activity);

        Long id = activity.getId();

        Activity retrievedActivity = activityService.getActivityById(id);

        assertNotNull(retrievedActivity);
        assertEquals(id, retrievedActivity.getId());
    }

    @Test
    public void testDeleteActivityById() {
        Activity activity = new Activity();

        activity = activityRepository.save(activity);

        Long id = activity.getId();

        activityService.deleteActivityById(id);

        Activity deletedActivity = activityRepository.findById(id).orElse(null);
        assertNull(deletedActivity);
    }
}
