package myboot.app.service;

import myboot.app.dao.CVRepository;
import myboot.app.model.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CVService {

    private final CVRepository cvRepository;

    @Autowired
    public CVService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Transactional
    public CV saveCV(CV cv) {
        return cvRepository.save(cv);
    }

    public List<CV> getAllCVs() {
        return (List<CV>) cvRepository.findAll();
    }

    public CV getCVById(Long id) {
        return cvRepository.findById(id).orElse(null);
    }


    public CV updateCV(Long id, CV updatedCV) {
        if (cvRepository.findById(id).isPresent()) {
            updatedCV.setId(id);
            return cvRepository.save(updatedCV);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteCVById(Long id) {
        cvRepository.deleteById(id);
    }
}
