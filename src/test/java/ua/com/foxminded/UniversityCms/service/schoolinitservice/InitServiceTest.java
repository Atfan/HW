package ua.com.foxminded.UniversityCms.service.schoolinitservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.UniversityCms.AppStarter;
import ua.com.foxminded.UniversityCms.UniversityCmsApplication;
import ua.com.foxminded.UniversityCms.dao.group.GroupRepository;
import ua.com.foxminded.UniversityCms.dao.student.StudentRepository;
import ua.com.foxminded.UniversityCms.dao.subject.SubjectRepository;
import ua.com.foxminded.UniversityCms.dao.teacher.TeacherRepository;
import ua.com.foxminded.UniversityCms.dao.timetable.TimetableRepository;
import ua.com.foxminded.UniversityCms.utils.TestConteinersConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@ContextConfiguration(classes = UniversityCmsApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class InitServiceTest {

    @MockBean
    private AppStarter appStarter;

    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;

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
    private SchoolInitService schoolInitService;

    @Test
    @Sql("classpath:addDatadase.sql")
    void deleteAllRowsInDB_ShouldDeleteAllDataInDB_WhenCalled() {

        schoolInitService.deleteAllRowsInDB();

        assertThat(groupRepository.findAll()).isEmpty();
        assertThat(subjectRepository.findAll()).isEmpty();
        assertThat(studentRepository.findAll()).isEmpty();
        assertThat(timetableRepository.findAll()).isEmpty();
        assertThat(teacherRepository.findAll()).isEmpty();
    }

}
