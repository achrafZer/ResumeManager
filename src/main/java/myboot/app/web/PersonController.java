package myboot.app.web;

import myboot.app.model.Person;
import myboot.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping()
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search-first-name")
    public ResponseEntity<List<Person>> getPersonsByFirstName(@RequestParam String firstName) {
        List<Person> personList = personService.getPersonsByFirstName(firstName);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/search-last-name")
    public ResponseEntity<List<Person>> getPersonsByLastName(@RequestParam String lastName) {
        List<Person> personList = personService.getPersonsByLastName(lastName);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/search-by-activity-title")
    public ResponseEntity<List<Person>> getPersonByPartOfActivityTitle(@RequestParam String activityTitle) {
        List<Person> personList = personService.getPersonsByPartOfActivityTitle(activityTitle);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Person>> search(@RequestParam String query) {
        List<Person> resultSet = new ArrayList<>();
        resultSet.addAll(personService.getPersonsByFirstName(query));
        resultSet.addAll(personService.getPersonsByLastName(query));
        resultSet.addAll(personService.getPersonsByPartOfActivityTitle(query));

        return ResponseEntity.ok(resultSet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id,@Valid @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
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
    public void triggerException() {
        throw new RuntimeException("Exception déclenchée intentionnellement");
    }
}
