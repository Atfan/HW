package ua.com.foxminded.UniversityCms.service.schoolinitservice;

import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.model.*;

import java.util.List;

@Service
public interface SchoolInitService {

    List<Subject> findAllSubjects();

    List<Student> findAllStudents();

    List<Teacher> findAllTeachers();

    List<Group> findAllGroups();

    void deleteAllRowsInDB();

    void saveGroupList(List<Group> groups);

    void saveSubjectsList(List<Subject> subjects);

    void saveStudentsList(List<Student> students);

    void saveTeachersList(List<Teacher> teachers);

    void saveTimetablesList(List<Timetable> timetables);
}
