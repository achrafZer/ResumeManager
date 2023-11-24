package myboot.app.web;

import myboot.app.dao.ActivityRepository;
import myboot.app.model.Activity;
import myboot.app.model.Person;
import myboot.app.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @PostMapping
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody Activity activity) {
        Activity savedActivity = activityService.saveActivity(activity);
        return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Optional<Activity> optionalActivity = Optional.ofNullable(activityService.getActivityById(id));
        return optionalActivity.map(activity -> new ResponseEntity<>(activity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search-by-activity-title")
    public ResponseEntity<List<Activity>> getActivityByTitle(@RequestParam String activityTitle) {
        List<Activity> activitieList = activityService.getActivityByTitle(activityTitle);
        return new ResponseEntity<>(activitieList, HttpStatus.OK);
    }

    @GetMapping("/search-by-person-id")
    public ResponseEntity<List<Activity>> getActivitiesByPerson(@RequestParam Long id) {
        List<Activity> activitieList = activityService.getActivitiesByPersonId(id);
        return new ResponseEntity<>(activitieList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @Valid @RequestBody Activity updatedActivity) {
        try {
            Activity activity = activityService.updateActivity(id, updatedActivity);
            return new ResponseEntity<>(activity, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        if (activityService.existsById(id)) {
            activityService.deleteActivityById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
