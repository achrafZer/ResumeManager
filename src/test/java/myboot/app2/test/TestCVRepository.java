package myboot.app2.test;

import myboot.app2.dao.CVRepository;
import myboot.app2.model.CV;
import myboot.app2.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@DataJpaTest
class TestCVRepository {


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CVRepository cvRepository;

    private CV cv;
    private Person person;

    @BeforeEach
    public void setUp() throws ParseException {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        person.setBirthDate(birthday);
        person.setPassword("JohnsPassword");
        entityManager.persist(person);

        cv = new CV();
        cv.setPerson(person);
        entityManager.persist(cv);
    }

    @AfterEach
    public void tearDown() {
        entityManager.remove(cv);
        entityManager.remove(person);
    }

    @Test
    void whenSave_thenPersistData() {
        CV newCV = new CV();
        newCV.setPerson(person);
        CV saved = cvRepository.save(newCV);
        Optional<CV> found = cvRepository.findById(saved.getId());
        assertThat(found).isPresent();
    }

    @Test
    void saveCV_withNullPerson_shouldThrowException() {
        CV cv = new CV();
        assertThrows(Exception.class, () -> {
            cvRepository.save(cv);
        });
    }

    @Test
    void whenFindById_thenReturnCV() {
        Optional<CV> found = cvRepository.findById(cv.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getPerson()).isEqualTo(person);
    }

    @Test
    void findById_withInvalidId_shouldReturnEmptyOptional() {
        Optional<CV> foundCV = cvRepository.findById(999L);
        assertThat(foundCV).isNotPresent();
    }

    @Test
    void updateCV_withValidData_shouldUpdate() {

        Person updatedPerson = person;
        updatedPerson.setFirstName("Jane");
        updatedPerson.setLastName("Doe");
        cv.setPerson(updatedPerson);

        cvRepository.save(cv);

        CV updatedCV = entityManager.find(CV.class, cv.getId());
        assertEquals("Jane", updatedCV.getPerson().getFirstName());
    }

    @Test
    void whenDelete_thenRemoveData() {
        cvRepository.delete(cv);
        Optional<CV> found = cvRepository.findById(cv.getId());
        assertThat(found).isNotPresent();
    }


}
