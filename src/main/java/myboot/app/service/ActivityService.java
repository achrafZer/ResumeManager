package myboot.app.service;

import myboot.app.dao.ActivityRepository;
import myboot.app.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for managing activities.
 * Provides methods for CRUD operations and specific queries related to {@link Activity}.
 */
@Service
public class ActivityService {

    @Autowired
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * Saves an activity to the repository.
     */
    @Transactional
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    /**
     * Gets an activity by its ID.
     *
     * @param id The ID of the activity.
     * @return The activity, or null if not found.
     */
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    /**
     * Gets all activities.
     *
     * @return A list of all activities.
     */
    @Transactional
    public List<Activity> getAllActivities() {
        return new ArrayList<>(activityRepository.findAll());
    }

    /**
     * Gets activities by their titles or a part of it.
     *
     * @param title The title of the activities.
     * @return A list of activities with the given title.
     */
    @Transactional
    public List<Activity> getActivityByTitle(String title) {
        return activityRepository.findActivityByTitle(title);
    }

    /**
     * Deletes an activity by its ID.
     *
     * @param id The ID of the activity to delete.
     */
    @Transactional
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    /**
     * Updates an existing activity.
     *
     * @param id              The ID of the activity to update.
     * @param updatedActivity The updated activity data.
     * @return The updated activity.
     * @throws IllegalArgumentException If the activity with the specified ID is not found.
     */
    @Transactional
    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id).map(activity -> {
            activity.setTitle(updatedActivity.getTitle());
            activity.setDescription(updatedActivity.getDescription());
            activity.setStartYear(updatedActivity.getStartYear());
            activity.setEndYear(updatedActivity.getEndYear());
            activity.setNature(updatedActivity.getNature());

            return activityRepository.save(activity);
        }).orElseThrow(() -> new EntityNotFoundException("Activity not found with id " + id));
    }

    /**
     * Checks if an activity exists by its ID.
     *
     * @param id The ID of the activity.
     * @return true if an activity with the given ID exists, false otherwise.
     */
    public boolean existsById(Long id) {
        return activityRepository.existsById(id);
    }

    public List<Activity> getActivitiesByPersonId(Long id) {
        return activityRepository.getActivitiesByPersonId(id);
    }

}
