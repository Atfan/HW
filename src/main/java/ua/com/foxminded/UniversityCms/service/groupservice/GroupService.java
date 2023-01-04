package ua.com.foxminded.UniversityCms.service.groupservice;

import ua.com.foxminded.UniversityCms.model.Group;

import java.util.List;

public interface GroupService {

    void save(Group group) ;

    int[] saveStudentsList(List<Group> groups) ;

    void update(Group group) ;

    void delete(Group group) ;

    List<Group> findAll() ;

    void deleteAll() ;
}
