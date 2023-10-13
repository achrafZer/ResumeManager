package myboot.app2.test;

import myboot.app2.dao.ActivityRepository;
import myboot.app2.model.Activity;
import myboot.app2.model.ActivityNature;
import myboot.app2.model.CV;
import myboot.app2.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@DataJpaTest
class TestActivityRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ActivityRepository activityRepository;

    Activity activity;

    @BeforeEach
    public void setUp() throws ParseException {
        activity = new Activity();
        activity.setStartYear(2023);
        activity.setNature(ActivityNature.EDUCATION);
        activity.setTitle("Java Training");
        entityManager.persist(activity);
    }

    @AfterEach
    public void tearDown() {
        entityManager.remove(activity);
    }

    @Test
    void testSaveActivity() {
        Activity activity1 = activityRepository.save(activity);
        assertNotNull(activity1.getId());
    }

    @Test
    void testFindById() {
        Activity foundActivity = activityRepository.findById(activity.getId()).orElse(null);
        assertNotNull(foundActivity);
    }

    @Test
    void testUpdateActivity() {
        activity.setTitle("Advanced Java Training");
        activityRepository.save(activity);

        Activity updatedActivity = entityManager.find(Activity.class, activity.getId());
        assertEquals("Advanced Java Training", updatedActivity.getTitle());
    }

    @Test
    void testDeleteActivity() {
        activityRepository.deleteById(activity.getId());

        Activity deletedActivity = entityManager.find(Activity.class, activity.getId());
        assertNull(deletedActivity);
    }
}
