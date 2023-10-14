package myboot.app2.web;

import myboot.app2.model.Person;
import myboot.app2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Person person = personService.updatePerson(id, updatedPerson);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (personService.getPersonById(id) != null) {
            personService.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("persons/search-first-name")
    public ResponseEntity<List<Person>> getPersonsByFirstName(@RequestParam String firstName) {
        List<Person> personList = personService.getPersonsByFirstName(firstName);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("persons/search-last-name")
    public ResponseEntity<List<Person>> getPersonsByLastName(@RequestParam String lastName) {
        List<Person> personList = personService.getPersonsByLastName(lastName);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

//    @GetMapping("persons/search-by-activity-title")
//    public ResponseEntity<List<Person>> getPersonByPartOfActivityTitle(@RequestParam String activityTitle) {
//        List<Person> personList = personService.getPersonsByPartOfActivityTitle(activityTitle);
//        return new ResponseEntity<>(personList, HttpStatus.OK);
//    }
}
