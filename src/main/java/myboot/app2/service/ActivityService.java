package myboot.app2.service;

import myboot.app2.dao.ActivityRepository;
import myboot.app2.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
