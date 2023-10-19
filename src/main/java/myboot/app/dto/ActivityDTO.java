package myboot.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import myboot.app.model.ActivityNature;
import myboot.app.model.CV;

import javax.validation.constraints.*;

@Getter
@Setter
public class ActivityDTO {

    @Getter
    private Long id;

    @NotNull(message = "Start year is required")
    @Min(value = 1900, message = "Start year should not be less than 1900")
    @Max(value = 2023, message = "Start year should be in the past")
    private Integer startYear;

    @NotNull(message = "End year is required")
    @Min(value = 1900, message = "End year should not be less than 1900")
    private Integer endYear;

    @NotNull
    private ActivityNature nature;

    @NotEmpty(message = "Title should not be empty")
    private String title;

    @Size(max = 100000, message = "Too long description")
    private String description;

    @JsonIgnore
    private CV cv;
}
