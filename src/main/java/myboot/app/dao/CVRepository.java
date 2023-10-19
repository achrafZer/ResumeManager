package myboot.app.dao;

import myboot.app.model.CV;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CVRepository extends CrudRepository<CV, Long> {

}
