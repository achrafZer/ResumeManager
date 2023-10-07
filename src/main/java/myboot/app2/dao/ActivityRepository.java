package myboot.app2.dao;

import myboot.app2.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
