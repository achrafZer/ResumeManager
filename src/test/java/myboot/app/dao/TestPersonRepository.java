package myboot.app.dao;


import myboot.app.dao.PersonRepository;
import myboot.app.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.validation.ConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@DataJpaTest
class TestPersonRepository {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    Person person;

    @AfterEach
    public void tearDown() {
        entityManager.remove(person);
    }


    @BeforeEach
    public void setUp() throws ParseException {
        person = new Person();
        person.setFirstName("Jean");
        person.setLastName("SAMSON");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        person.setBirthDate(birthday);
        person.setEmail("jeansamson@email.com");
        person.setPassword("JeanPassword");
        entityManager.persist(person);
    }

    @Test
    void savePerson_withValidData() throws Exception {
        Person newPerson = new Person();
        newPerson.setFirstName("Jean");
        newPerson.setLastName("SAMSON");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        newPerson.setBirthDate(birthday);
        newPerson.setEmail("jeansamson@email.com");
        newPerson.setPassword("JeanPassword");
        Person saved = personRepository.save(newPerson);

        Optional<Person> found = personRepository.findById(saved.getId());
        assertTrue(found.isPresent());

        personRepository.delete(newPerson);

    }

    @Test
    void savePerson_withUnvalidFirstName() throws ParseException { //name containing a number
        Person newPerson = new Person();
        newPerson.setFirstName("Jean2");
        newPerson.setLastName("SAMSON");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        newPerson.setBirthDate(birthday);
        newPerson.setEmail("jeansamson@email.com");
        newPerson.setPassword("JeanPassword");
        assertThrows(ConstraintViolationException.class, () -> {
            personRepository.save(newPerson);
        });
    }

    @Test
    void savePerson_withUnvalidLastName() throws ParseException { //name containing an empty string
        Person newPerson = new Person();
        newPerson.setFirstName("Jean");
        newPerson.setLastName("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        newPerson.setBirthDate(birthday);
        newPerson.setEmail("jeansamson@email.com");
        newPerson.setPassword("JeanPassword");
        assertThrows(ConstraintViolationException.class, () -> {
            personRepository.save(newPerson);
        });
    }

    @Test
    void savePerson_withUnvalidEmail() throws ParseException { //email not containing '@'
        Person newPerson = new Person();
        newPerson.setFirstName("Jean");
        newPerson.setLastName("SAMSON");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        newPerson.setBirthDate(birthday);
        newPerson.setEmail("jeansamson.email.com");
        newPerson.setPassword("JeanPassword");
        assertThrows(ConstraintViolationException.class, () -> {
            personRepository.save(newPerson);
        });
    }

    @Test
    void savePerson_withUnvalidWebSite() throws ParseException { //webSite not containing a '.'
        Person newPerson = new Person();
        newPerson.setFirstName("Jean");
        newPerson.setLastName("SAMSON");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        newPerson.setBirthDate(birthday);
        newPerson.setEmail("jeansamson@email.com");
        newPerson.setPassword("JeanPassword");
        newPerson.setWebsite("myWrongWebsite");
        assertThrows(ConstraintViolationException.class, () -> {
            personRepository.save(newPerson);
        });
    }

    @Test
    void whenFindById_thenReturnPerson() {
        Optional<Person> found = personRepository.findById(person.getId());
        assertTrue(found.isPresent());
        assertEquals("Jean", found.get().getFirstName());
    }

    @Test
    void updatePerson_withValidData_shouldUpdate() {

        person.setPassword("new-password");

        personRepository.save(person);

        Person updatedPerson = entityManager.find(Person.class, person.getId());
        assertEquals("new-password", updatedPerson.getPassword());
    }

    @Test
    void whenDelete_thenRemoveData() {
        personRepository.delete(person);
        Optional<Person> found = personRepository.findById(person.getId());
        assertThat(found).isNotPresent();
    }

}