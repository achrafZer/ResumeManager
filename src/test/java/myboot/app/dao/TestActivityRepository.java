package myboot.app.dao;

import myboot.app.model.Activity;
import myboot.app.model.ActivityNature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.validation.ConstraintViolationException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        activity.setEndYear(2024);
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
    void testSaveUnvalidActivity_unvalidStartYear() { //Start year > actual year
        Activity activity1 = new Activity();
        activity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        activity1.setStartYear(2024);
        activity1.setEndYear(2025);
        activity1.setTitle("my first activity");
        assertThrows(ConstraintViolationException.class, () -> {
            activityRepository.save(activity1);
        });
    }

    @Test
    void testSaveUnvalidActivity_unvalidEndYear() { //startYear > EndYear
        Activity activity1 = new Activity();
        activity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        activity1.setStartYear(2023);
        activity1.setEndYear(2022);
        activity1.setTitle("my first activity");
        assertThrows(ConstraintViolationException.class, () -> {
            activityRepository.save(activity1);
        });
    }

    @Test
    void testSaveUnvalidActivity_nullTitle() { //startYear > EndYear
        Activity activity1 = new Activity();
        activity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        activity1.setStartYear(2021);
        activity1.setEndYear(2022);
        activity1.setTitle(null);
        assertThrows(ConstraintViolationException.class, () -> {
            activityRepository.save(activity1);
        });
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
