package myboot.app.service;

import myboot.app.dao.PersonRepository;
import myboot.app.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for handling business logic related to Person entities.
 * This class interacts with the PersonRepository to perform CRUD operations.
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Constructs a PersonService with the necessary PersonRepository.
     *
     * @param personRepository The repository used for person-related operations.
     */
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Saves a person entity in the database. Encrypts the password before saving.
     *
     * @param person The person to be saved.
     * @return The saved person entity.
     */
    @Transactional
    public Person savePerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    /**
     * Retrieves a person by their ID.
     *
     * @param id The ID of the person to retrieve.
     * @return The person if found, or null otherwise.
     */
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a person by their ID.
     *
     * @param id The ID of the person to delete.
     */
    @Transactional
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

    /**
     * Retrieves all persons from the database.
     *
     * @return A list of all persons.
     */
    public List<Person> getAllPersons() {
        return new ArrayList<>(personRepository.findAll());
    }

    /**
     * Updates a person's details.
     *
     * @param id           The ID of the person to update.
     * @param updatedPerson The updated person information.
     * @return The updated person entity.
     */
    @Transactional
    public Person updatePerson(Long id, Person updatedPerson) {
        if (personRepository.findById(id).isPresent()) {
            updatedPerson.setId(id);
            personRepository.save(updatedPerson);
        }
        return updatedPerson;
    }

    /**
     * Retrieves a list of persons with a specific first name.
     *
     * @param firstName The first name to search for.
     * @return A list of persons with the given first name.
     */
    public List<Person> getPersonsByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    /**
     * Retrieves a list of persons with a specific last name.
     *
     * @param lastName The last name to search for.
     * @return A list of persons with the given last name.
     */
    public List<Person> getPersonsByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    /**
     * Retrieves a list of persons who have an activity with a title containing the specified string.
     *
     * @param title The title fragment to search for in activities.
     * @return A list of persons who have activities with titles containing the specified string.
     */
    public List<Person> getPersonsByPartOfActivityTitle(String title) {
        return personRepository.findByActivityTitle(title);
    }

    /**
     * Searches for persons based on a query string.
     *
     * @param query The query string to use for searching.
     * @return A list of persons matching the query.
     */
    public List<Person> search(String query) {
        return personRepository.search(query);
    }
}
