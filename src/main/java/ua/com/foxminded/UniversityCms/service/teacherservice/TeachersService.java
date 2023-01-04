package ua.com.foxminded.UniversityCms.service.teacherservice;

import ua.com.foxminded.UniversityCms.model.Teacher;

import java.util.List;

public interface TeachersService {
    void save(Teacher teacher) ;

    int[] saveStudentsList(List<Teacher> teachers) ;

    void update(Teacher teacher) ;

    void delete(Teacher teacher) ;

    List<Teacher> findAll() ;

    void deleteAll() ;

}
