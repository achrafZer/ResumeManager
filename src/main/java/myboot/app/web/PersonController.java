package myboot.app.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import myboot.app.model.Person;
import myboot.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping()
    @Operation(summary = "Créer une nouvelle personne")
    @ApiResponse(responseCode = "201", description = "Personne créée avec succès")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Récupérer toutes les personnes")
    @ApiResponse(responseCode = "200", description = "Liste de toutes les personnes")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une personne par son ID")
    @ApiResponse(responseCode = "200", description = "Personne trouvée")
    @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search-first-name")
    @Operation(summary = "Rechercher des personnes par prénom")
    @ApiResponse(responseCode = "200", description = "Liste des personnes correspondantes")
    public ResponseEntity<List<Person>> getPersonsByFirstName(@RequestParam String firstName) {
        List<Person> personList = personService.getPersonsByFirstName(firstName);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/search-last-name")
    @Operation(summary = "Rechercher des personnes par nom")
    @ApiResponse(responseCode = "200", description = "Liste des personnes correspondantes")
    public ResponseEntity<List<Person>> getPersonsByLastName(@RequestParam String lastName) {
        List<Person> personList = personService.getPersonsByLastName(lastName);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/search-by-activity-title")
    @Operation(summary = "Rechercher des personnes par titre d'activité")
    @ApiResponse(responseCode = "200", description = "Liste des personnes correspondantes")
    public ResponseEntity<List<Person>> getPersonByPartOfActivityTitle(@RequestParam String activityTitle) {
        List<Person> personList = personService.getPersonsByPartOfActivityTitle(activityTitle);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Recherche générale de personnes, par nom, prénom et titre d'activité")
    @ApiResponse(responseCode = "200", description = "Résultat de la recherche")
    public ResponseEntity<List<Person>> search(@RequestParam String query) {
        List<Person> resultSet = new ArrayList<>();
        resultSet.addAll(personService.getPersonsByFirstName(query));
        resultSet.addAll(personService.getPersonsByLastName(query));
        resultSet.addAll(personService.getPersonsByPartOfActivityTitle(query));

        return ResponseEntity.ok(resultSet);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une personne")
    @ApiResponse(responseCode = "200", description = "Personne mise à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id,@Valid @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une personne")
    @ApiResponse(responseCode = "204", description = "Personne supprimée avec succès")
    @ApiResponse(responseCode = "404", description = "Personne non trouvée")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (personService.getPersonById(id) != null) {
            personService.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/exception-test")
    @Operation(summary = "Déclencher une exception de test")
    @ApiResponse(responseCode = "500", description = "Exception déclenchée")
    public void triggerException() {
        throw new RuntimeException("Exception déclenchée intentionnellement");
    }
}
