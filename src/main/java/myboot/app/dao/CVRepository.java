package myboot.app.dao;

import myboot.app.model.CV;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository interface for managing {@link CV} entities.
 * Extends from Spring's {@link CrudRepository} to provide basic CRUD operations.
 *
 * @author Achraf
 * @version 1.0
 */
@Repository
@Transactional
public interface CVRepository extends CrudRepository<CV, Long> {
    Optional<CV> findByPersonId(Long personId);
}
