package myboot.app.service;

import myboot.app.model.Activity;
import myboot.app.model.ActivityNature;
import myboot.app.service.ActivityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TestActivityService {

    @Autowired
    private ActivityService activityService;

    private Activity testActivity;

    @BeforeEach
    public void setUp() {
        testActivity = new Activity();
        testActivity.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        testActivity.setTitle("Software Developer");
        testActivity.setDescription("Development of applications.");
        testActivity.setStartYear(2015);
        testActivity.setEndYear(2021);
    }

    @Test
    void testSaveActivity() {
        Activity savedActivity = activityService.saveActivity(testActivity);
        assertNotNull(savedActivity.getId());
        assertEquals(testActivity.getTitle(), savedActivity.getTitle());
    }

    @Test
    void testGetActivityByTitle() {
        Activity activity = activityService.saveActivity(testActivity);
        assertNotNull(activityService.getActivityByTitle("Software Developer"));
    }

    @Test
    void testGetAllActivities() {
        assertEquals(4, activityService.getAllActivities().size());
    }

    @Test
    void testUpdateActivity() {
        Activity savedActivity = activityService.saveActivity(testActivity);

        savedActivity.setTitle("Senior Software Developer");
        Activity updatedActivity = activityService.updateActivity(savedActivity.getId(), savedActivity);

        assertEquals("Senior Software Developer", updatedActivity.getTitle());
    }

    @Test
    void testUnvaliUpdateActivity() { //update with an inexistant id
        assertThrows(IllegalArgumentException.class, () -> {
            activityService.updateActivity(5L, testActivity);
        });
    }

    @Test
    void testDeleteActivity() {
        Activity savedActivity = activityService.saveActivity(testActivity);
        activityService.deleteActivityById(savedActivity.getId());
        assertNull(activityService.getActivityById(savedActivity.getId()));
    }

    @AfterEach
    public void tearDown() {
        testActivity = null;
    }
}
