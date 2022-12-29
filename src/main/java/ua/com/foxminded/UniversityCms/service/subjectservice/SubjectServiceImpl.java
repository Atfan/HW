package ua.com.foxminded.UniversityCms.service.subjectservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.dao.subject.SubjectRepository;
import ua.com.foxminded.UniversityCms.model.Subject;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public int[] saveStudentsList(List<Subject> subjects) {
        subjectRepository.saveAll(subjects);
        return new int[0];
    }

    @Override
    public void update(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void deleteAll() {
        subjectRepository.deleteAll();
    }
}
