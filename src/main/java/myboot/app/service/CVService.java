package myboot.app.service;

import myboot.app.dao.CVRepository;
import myboot.app.model.CV;
import myboot.app.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing CV (Curriculum Vitae) entities.
 * Provides methods for CRUD operations on CVs.
 */
@Service
public class CVService {

    private final CVRepository cvRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    public CVService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    /**
     * Saves a CV to the repository.
     *
     * @param cv The CV to be saved.
     * @return The saved CV.
     */
    @Transactional
    public CV saveCV(CV cv) {
        CV savedCV = cvRepository.save(cv);

        Person person = savedCV.getPerson();
        if (person != null) {
            personService.savePerson(person);
        }

        return savedCV;    }

    /**
     * Retrieves all CVs from the repository.
     *
     * @return A list of all CVs.
     */
    public List<CV> getAllCVs() {
        return (List<CV>) cvRepository.findAll();
    }

    /**
     * Retrieves a CV by its ID.
     *
     * @param id The ID of the CV.
     * @return The CV, or null if not found.
     */
    public CV getCVById(Long id) {
        return cvRepository.findById(id).orElse(null);
    }

    /**
     * Updates a CV identified by its ID with new information.
     *
     * @param id The ID of the CV to update.
     * @param updatedCV The new CV data.
     * @return The updated CV, or null if the original CV was not found.
     */
    public CV updateCV(Long id, CV updatedCV) {
        if (cvRepository.findById(id).isPresent()) {
            updatedCV.setId(id);
            return cvRepository.save(updatedCV);
        } else {
            return null;
        }
    }

    /**
     * Deletes a CV by its ID.
     *
     * @param id The ID of the CV to delete.
     */
    @Transactional
    public void deleteCVById(Long id) {
        cvRepository.deleteById(id);
    }

    @Transactional
    public CV getCvByPersonId(Long personId) {
        // Recherche du CV par l'ID de la personne
        Optional<CV> cvOptional = cvRepository.findByPersonId(personId);
        return cvOptional.orElseThrow(() -> new EntityNotFoundException("CV non trouvé pour l'ID de la personne : " + personId));
    }
}
