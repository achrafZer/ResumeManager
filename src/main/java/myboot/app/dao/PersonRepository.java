package myboot.app.dao;


import myboot.app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository interface for managing {@link Person} entities.
 * Provides CRUD operations inherited from {@link JpaRepository} and custom queries
 * to find persons by specific attributes.
 *
 * @author Achraf
 * @version 1.0
 */
@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Retrieves a list of persons whose first names contain the specified string.
     * The search is case-insensitive.
     *
     * @param firstName The partial or complete first name to search for.
     * @return A list of persons matching the search criteria.
     */
    @Query("SELECT p FROM Person p WHERE UPPER(p.firstName) LIKE UPPER(CONCAT('%', :firstName, '%'))")
    List<Person> findByFirstName(String firstName);

    /**
     * Retrieves a list of persons whose last names contain the specified string.
     * The search is case-insensitive.
     *
     * @param lastName The partial or complete last name to search for.
     * @return A list of persons matching the search criteria.
     */
    @Query("SELECT p FROM Person p WHERE UPPER(p.lastName) LIKE UPPER(CONCAT('%', :lastName, '%'))")
    List<Person> findByLastName(String lastName);

    /**
     * Retrieves a list of persons who have an activity with the specified title in their CV.
     *
     * @param title The title of the activity to search for.
     * @return A list of persons who have the specified activity title in their CV.
     */
    @Query("SELECT DISTINCT p FROM Person p JOIN p.cv cv JOIN cv.activities a WHERE a.title LIKE CONCAT('%', :title , '%')")
    List<Person> findByActivityTitle(String title);

    /**
     * Retrieves a person whose email address contains the specified string.
     * The search is case-insensitive.
     *
     * @param email The partial or complete email to search for.
     * @return The person matching the search criteria, or null if no match is found.
     */
    @Query("SELECT p FROM Person p WHERE UPPER(p.email) LIKE UPPER(CONCAT('%', :email, '%'))")
    Person findByEmail(String email);

    @Query("SELECT DISTINCT p FROM Person p " +
            "LEFT JOIN p.cv cv " +
            "LEFT JOIN cv.activities a " +
            "WHERE (UPPER(p.firstName) LIKE UPPER(CONCAT('%', :searchQuery, '%')) " +
            "OR UPPER(p.lastName) LIKE UPPER(CONCAT('%', :searchQuery, '%')) " +
            "OR UPPER(a.title) LIKE UPPER(CONCAT('%', :searchQuery, '%')))")
    List<Person> search(@Param("searchQuery") String searchQuery);


    /**
     * Checks if a person exists with the provided email.
     *
     * @param email the email to search for
     * @return true if a person with the provided email exists, false otherwise
     */
    boolean existsByEmail(String email);

}
