package ua.com.foxminded.UniversityCms.service.studentservice;

import ua.com.foxminded.UniversityCms.model.Student;

import java.util.List;

public interface StudentService {
    void save(Student student) ;

    int[] saveStudentsList(List<Student> students) ;

    void update(Student student) ;

    void delete(Student student) ;

    List<Student> findAll() ;

    void deleteAll() ;

    List<Student> findByFirstNameAndLastNameLike(String firstName, String lastName);

    List<Student> findByIdLike(Long id);

}
