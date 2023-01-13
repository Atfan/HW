package ua.com.foxminded.UniversityCms.service.subjectservice;

import ua.com.foxminded.UniversityCms.model.Subject;

import java.util.List;

public interface SubjectService {
    void save(Subject subject) ;

    int[] saveStudentsList(List<Subject> subjects) ;

    void update(Subject subject) ;

    void delete(Subject subject) ;

    List<Subject> findAll() ;

    List<Subject> findBySubjectNameLike(String subjectName);

    void deleteAll() ;

}
