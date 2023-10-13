package myboot.app2.test;

import myboot.app2.dao.ActivityRepository;
import myboot.app2.model.Activity;
import myboot.app2.model.ActivityNature;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class TestActivityRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    void testSaveActivity() {
        Activity activity = new Activity();
        activity.setStartYear(2023);
        activity.setNature(ActivityNature.EDUCATION);
        activity.setTitle("Java Training");

        activity = activityRepository.save(activity);

        assertNotNull(activity.getId());
    }

    @Test
    void testFindById() {
        Activity activity = new Activity();
        activity.setStartYear(2023);
        activity.setNature(ActivityNature.EDUCATION);
        activity.setTitle("Java Training");
        activity = entityManager.persist(activity);

        Activity foundActivity = activityRepository.findById(activity.getId()).orElse(null);
        assertNotNull(foundActivity);
    }

    @Test
    void testUpdateActivity() {
        Activity activity = new Activity();
        activity.setStartYear(2023);
        activity.setNature(ActivityNature.EDUCATION);
        activity.setTitle("Java Training");
        activity = entityManager.persist(activity);

        activity.setTitle("Advanced Java Training");
        activityRepository.save(activity);

        Activity updatedActivity = entityManager.find(Activity.class, activity.getId());
        assertEquals("Advanced Java Training", updatedActivity.getTitle());
    }

    @Test
    void testDeleteActivity() {
        Activity activity = new Activity();
        activity.setStartYear(2023);
        activity.setNature(ActivityNature.EDUCATION);
        activity.setTitle("Java Training");
        activity = entityManager.persist(activity);

        activityRepository.deleteById(activity.getId());

        Activity deletedActivity = entityManager.find(Activity.class, activity.getId());
        assertNull(deletedActivity);
    }
}
