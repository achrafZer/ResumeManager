package myboot.app.test;

import myboot.app.dao.CVRepository;
import myboot.app.model.Activity;
import myboot.app.model.ActivityNature;
import myboot.app.model.CV;
import myboot.app.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        person.setFirstName("Jean");
        person.setLastName("SAMSON");
        person.setEmail("jean.amson@example.com");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("1990-01-01");
        person.setBirthDate(birthday);
        person.setPassword("JeanPassword");
        entityManager.persist(person);

        cv = new CV();
        cv.setPerson(person);

        Activity activity = new Activity();
        activity.setTitle("mon activité");
        activity.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        activity.setStartYear(2012);
        activity.setEndYear(2020);
        activity.setDescription("ghghgg");
        activity.setCv(cv);
        entityManager.persist(activity);
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
        updatedPerson.setFirstName("Achraf");
        updatedPerson.setLastName("ZERHOUNI");
        cv.setPerson(updatedPerson);

        cvRepository.save(cv);

        CV updatedCV = entityManager.find(CV.class, cv.getId());
        assertEquals("Achraf", updatedCV.getPerson().getFirstName());
    }

    @Test
    void whenDelete_thenRemoveData() {
        cvRepository.delete(cv);
        Optional<CV> found = cvRepository.findById(cv.getId());
        assertThat(found).isNotPresent();
    }


}
