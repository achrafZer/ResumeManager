package myboot.app.mapper;

import myboot.app.dto.ActivityDTO;
import myboot.app.model.Activity;

public class ActivityMapper {
    public static Activity toEntity(ActivityDTO dto) {
        Activity activity = new Activity();
        activity.setDescription(dto.getDescription());
        activity.setEndYear(dto.getEndYear());
        activity.setId(dto.getId());
        activity.setNature(dto.getNature());
        activity.setStartYear(dto.getStartYear());
        activity.setTitle(dto.getTitle());
        return activity;
    }

    public static ActivityDTO toDTO(Activity activity) {
        ActivityDTO dto = new ActivityDTO();
        dto.setDescription(activity.getDescription());
        dto.setEndYear(activity.getEndYear());
        dto.setId(activity.getId());
        dto.setNature(activity.getNature());
        dto.setStartYear(activity.getStartYear());
        dto.setTitle(activity.getTitle());
        return dto;
    }
}
