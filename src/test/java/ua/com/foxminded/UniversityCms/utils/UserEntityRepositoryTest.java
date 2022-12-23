package ua.com.foxminded.UniversityCms.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.foxminded.UniversityCms.dao.group.GroupRepository;
import ua.com.foxminded.UniversityCms.dao.student.StudentRepository;
import ua.com.foxminded.UniversityCms.dao.subject.SubjectRepository;
import ua.com.foxminded.UniversityCms.dao.teacher.TeacherRepository;
import ua.com.foxminded.UniversityCms.dao.timetable.TimetableRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserEntityRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(groupRepository).isNotNull();
        assertThat(subjectRepository).isNotNull();
        assertThat(studentRepository).isNotNull();
        assertThat(timetableRepository).isNotNull();
        assertThat(teacherRepository).isNotNull();
    }

}
