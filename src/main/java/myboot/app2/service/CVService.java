package myboot.app2.service;

import myboot.app2.dao.CVRepository;
import myboot.app2.model.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public CV getCVById(Long id) {
        return cvRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteCVById(Long id) {
        cvRepository.deleteById(id);
    }
}
