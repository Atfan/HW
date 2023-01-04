package ua.com.foxminded.UniversityCms.service.schoolinitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.dao.group.GroupRepository;
import ua.com.foxminded.UniversityCms.dao.student.StudentRepository;
import ua.com.foxminded.UniversityCms.dao.subject.SubjectRepository;
import ua.com.foxminded.UniversityCms.dao.teacher.TeacherRepository;
import ua.com.foxminded.UniversityCms.dao.timetable.TimetableRepository;
import ua.com.foxminded.UniversityCms.dao.user.UserRepository;
import ua.com.foxminded.UniversityCms.model.*;

import java.util.List;

@Service
public class SchoolInitServiceImpl implements SchoolInitService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Teacher> findAllTeachers() { return teacherRepository.findAll(); }

    @Override
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public void deleteAllRowsInDB() {
        studentRepository.deleteAll();
        timetableRepository.deleteAll();
        subjectRepository.deleteAll();
        groupRepository.deleteAll();
        teacherRepository.deleteAll();
    }

    @Override
    public void saveGroupList(List<Group> groups) {
        groupRepository.saveAll(groups);
    }

    @Override
    public void saveSubjectsList(List<Subject> subjects) {
        subjectRepository.saveAll(subjects);
    }

    @Override
    public void saveStudentsList(List<Student> students) {
        studentRepository.saveAll(students);
    }

    @Override
    public void saveTeachersList(List<Teacher> teachers) {
        teacherRepository.saveAll(teachers);
    }

    @Override
    public void saveTimetablesList(List<Timetable> timetables) {
        timetableRepository.saveAll(timetables);
    }
}
