package myboot.app2.service;

import myboot.app2.dao.ActivityRepository;
import myboot.app2.dao.PersonRepository;
import myboot.app2.model.Activity;
import myboot.app2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(personRepository.findAll());
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        if (personRepository.findById(id).isPresent()) {
            updatedPerson.setId(id);
            personRepository.save(updatedPerson);
        }
        return updatedPerson;
    }

    public List<Person> getPersonsByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    public List<Person> getPersonsByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    public List<Person> getPersonsByPartOfActivityTitle(String title) {
        return personRepository.findByActivityTitle(title);
    }
}
