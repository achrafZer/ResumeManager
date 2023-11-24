package myboot.app.dao;

import myboot.app.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository interface for managing {@link Activity} entities.
 * Provides CRUD operations and a custom query to find activities by title.
 *
 * @author Achraf
 * @version 1.0
 */
@Repository
@Transactional
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    /**
     * Retrieves a list of activities that have a specified title.
     *
     * @param title The title of the activities to search for.
     * @return A list of activities matching the specified title.
     */
    @Query("SELECT a FROM Activity a WHERE a.title = :title")
    List<Activity> findActivityByTitle(String title);

    @Query("SELECT a FROM Activity a WHERE a.cv.person.id = :id")
    List<Activity> getActivitiesByPersonId(Long id);
}
