package myboot.app.dao;


import myboot.app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE UPPER(p.firstName) LIKE UPPER(CONCAT('%', :firstName, '%'))")
    List<Person> findByFirstName(String firstName);

    @Query("SELECT p FROM Person p WHERE UPPER(p.lastName) LIKE UPPER(CONCAT('%', :lastName, '%'))")
    List<Person> findByLastName(String lastName);

    @Query("SELECT DISTINCT p FROM Person p JOIN p.cv cv JOIN cv.activities a WHERE a.title LIKE CONCAT('%', :title , '%')")
    List<Person> findByActivityTitle(String title);

    @Query("SELECT p FROM Person p WHERE UPPER(p.email) LIKE UPPER(CONCAT('%', :email, '%'))")
    Person findByEmail(String email);

    /**
     * Checks if a person exists with the provided email.
     *
     * @param email the email to search for
     * @return true if a person with the provided email exists, false otherwise
     */
    boolean existsByEmail(String email);

}
