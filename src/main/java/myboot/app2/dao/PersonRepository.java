package myboot.app2.dao;


import myboot.app2.model.Person;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE UPPER(p.firstName) LIKE UPPER(CONCAT('%', :firstName, '%'))")
    List<Person> findByFirstName(String firstName);

    @Query("SELECT p FROM Person p WHERE UPPER(p.lastName) LIKE UPPER(CONCAT('%', :lastName, '%'))")
    List<Person> findByLastName(String lastName);

    @Query("SELECT DISTINCT p FROM Person p JOIN p.cv cv JOIN cv.activities a WHERE a.title LIKE CONCAT('%', :title , '%')")
    List<Person> findByActivityTitle(String title);
}
