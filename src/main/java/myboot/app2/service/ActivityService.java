package myboot.app2.service;

import myboot.app2.dao.ActivityRepository;
import myboot.app2.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Transactional
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Activity> getAllActivities() {
        return new ArrayList<>(activityRepository.findAll());
    }

    @Transactional
    public List<Activity> getActivityByTitle(String title) {
        return activityRepository.findActivityByTitle(title);
    }

    @Transactional
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        if (activityRepository.findById(id).isPresent()) {
            Activity existingActivity = activityRepository.findById(id).get();

            existingActivity.setStartYear(updatedActivity.getStartYear());
            existingActivity.setEndYear(updatedActivity.getEndYear());
            existingActivity.setNature(updatedActivity.getNature());
            existingActivity.setTitle(updatedActivity.getTitle());
            existingActivity.setDescription(updatedActivity.getDescription());
            existingActivity.setCv(updatedActivity.getCv());

            return activityRepository.save(existingActivity);
        } else {
            throw new IllegalArgumentException("Activity with id " + id + " not found.");
        }
    }


}
