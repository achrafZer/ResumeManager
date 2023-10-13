package myboot.app2.test;


import myboot.app2.dao.PersonRepository;
import myboot.app2.model.CV;
import myboot.app2.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@DataJpaTest  // C'est mieux pour tester les repositories que @SpringBootTest
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
        person.setFirstName("John");
        person.setLastName("Doe");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        person.setBirthDate(birthday);
        person.setEmail("johndoe@email.com");
        person.setPassword("JohnsPassword");
        entityManager.persist(person);
    }

    @Test
    public void savePerson_withValidData_shouldPersist() throws Exception {
        Person newPerson = new Person();
        newPerson.setFirstName("John");
        newPerson.setLastName("Doe");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        newPerson.setBirthDate(birthday);
        newPerson.setEmail("johndoe@email.com");
        newPerson.setPassword("JohnsPassword");
        Person saved = personRepository.save(newPerson);

        Optional<Person> found = personRepository.findById(saved.getId());
        assertThat(found.isPresent()).isTrue();
    }

    @Test
    public void whenFindById_thenReturnPerson() {
        Optional<Person> found = personRepository.findById(person.getId());
        assertThat(found.isPresent()).isTrue();
        assertEquals(found.get().getFirstName(), "John");
    }

    @Test
    public void updatePerson_withValidData_shouldUpdate() {

        person.setPassword("new-password");

        personRepository.save(person);

        Person updatedPerson = entityManager.find(Person.class, person.getId());
        assertEquals(updatedPerson.getPassword(), "new-password");
    }

    @Test
    public void whenDelete_thenRemoveData() {
        personRepository.delete(person);
        Optional<Person> found = personRepository.findById(person.getId());
        assertThat(found.isPresent()).isFalse();
    }

}