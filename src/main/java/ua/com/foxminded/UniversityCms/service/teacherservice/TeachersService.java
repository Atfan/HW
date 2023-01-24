package ua.com.foxminded.UniversityCms.service.teacherservice;

import ua.com.foxminded.UniversityCms.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeachersService {
    void save(Teacher teacher) ;

    int[] saveStudentsList(List<Teacher> teachers) ;

    void update(Teacher teacher) ;

    void delete(Teacher teacher) ;

    List<Teacher> findAll() ;

    void deleteAll() ;

    List<Teacher> findByFirstNameAndLastNameLike(String firstName, String lastName);

    Optional<Teacher> findById(Long id);
}
