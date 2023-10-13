package myboot.app2.test;


import myboot.app2.dao.PersonRepository;
import myboot.app2.model.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest  // C'est mieux pour tester les repositories que @SpringBootTest
class TestPersonRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSavePerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");

        person = personRepository.save(person);

        assertNotNull(person.getId());
    }

    @Test
    void testFindById() {
        Person person = new Person();
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setEmail("jane.doe@example.com");

        person = entityManager.persist(person);

        Person foundPerson = personRepository.findById(person.getId()).orElse(null);
        assertNotNull(foundPerson);
        assertEquals(person.getFirstName(), foundPerson.getFirstName());
    }

    @Test
    void testUpdatePerson() {
        Person person = new Person();
        person.setFirstName("Robert");
        person.setLastName("Smith");
        person.setEmail("robert.smith@example.com");

        person = entityManager.persist(person);
        person.setLastName("Jones");
        personRepository.save(person);

        Person updatedPerson = entityManager.find(Person.class, person.getId());
        assertEquals("Jones", updatedPerson.getLastName());
    }

    @Test
    void testDeletePerson() {
        Person person = new Person();
        person.setFirstName("Alice");
        person.setLastName("Johnson");
        person.setEmail("alice.johnson@example.com");

        person = entityManager.persist(person);
        personRepository.deleteById(person.getId());

        Person deletedPerson = entityManager.find(Person.class, person.getId());
        assertNull(deletedPerson);
    }
}