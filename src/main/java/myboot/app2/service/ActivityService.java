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
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    @Transactional
    public List<Activity> getAllActivities() {
        return new ArrayList<>(activityRepository.findAll());
    }

    @Transactional
    public List<Activity> getActivityByTitle(String title) {
        return activityRepository.findActivityByTitle(title);
    }

}
