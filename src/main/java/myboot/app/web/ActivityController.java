package myboot.app.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Créer une nouvelle activité")
    @ApiResponse(responseCode = "201", description = "Activité créée avec succès")
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody Activity activity) {
        Activity savedActivity = activityService.saveActivity(activity);
        return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Récupérer toutes les activités")
    @ApiResponse(responseCode = "200", description = "Liste des activités récupérée")
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une activité par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Activité trouvée"),
            @ApiResponse(responseCode = "404", description = "Activité non trouvée")
    })
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Optional<Activity> optionalActivity = Optional.ofNullable(activityService.getActivityById(id));
        return optionalActivity.map(activity -> new ResponseEntity<>(activity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search-by-activity-title")
    @Operation(summary = "Rechercher des activités par titre")
    @ApiResponse(responseCode = "200", description = "Activités correspondant au titre fourni")
    public ResponseEntity<List<Activity>> getActivityByTitle(@RequestParam String activityTitle) {
        List<Activity> activitieList = activityService.getActivityByTitle(activityTitle);
        return new ResponseEntity<>(activitieList, HttpStatus.OK);
    }

    @GetMapping("/search-by-person-id")
    @Operation(summary = "Récupérer des activités par l'ID de la personne dont le CV les contient")
    @ApiResponse(responseCode = "200", description = "Activités de la personne spécifiée")
    public ResponseEntity<List<Activity>> getActivitiesByPerson(@RequestParam Long id) {
        List<Activity> activitieList = activityService.getActivitiesByPersonId(id);
        return new ResponseEntity<>(activitieList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une activité")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Activité mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Activité non trouvée")
    })
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
    @Operation(summary = "Supprimer une activité")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Activité supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Activité non trouvée")
    })
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        if (activityService.existsById(id)) {
            activityService.deleteActivityById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
