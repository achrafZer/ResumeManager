package myboot.app2.test;

import myboot.app2.dao.CVRepository;
import myboot.app2.model.CV;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class TestCVRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CVRepository cvRepository;

    @Test
    void testSaveCV() {
        CV cv = new CV();

        cv = cvRepository.save(cv);

        assertNotNull(cv.getId());
    }

    @Test
    void testFindById() {
        CV cv = new CV();
        cv = entityManager.persist(cv);

        CV foundCV = cvRepository.findById(cv.getId()).orElse(null);
        assertNotNull(foundCV);
    }

    @Test
    void testUpdateCV() {
        CV cv = new CV();
        cv = entityManager.persist(cv);

        cvRepository.save(cv);

        CV updatedCV = entityManager.find(CV.class, cv.getId());
        assertNotNull(updatedCV);
    }

    @Test
    void testDeleteCV() {
        CV cv = new CV();
        cv = entityManager.persist(cv);
        cvRepository.deleteById(cv.getId());

        CV deletedCV = entityManager.find(CV.class, cv.getId());
        assertNull(deletedCV);
    }
}
