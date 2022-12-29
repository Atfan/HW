package ua.com.foxminded.UniversityCms.service.teacherservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.UniversityCms.AppStarter;
import ua.com.foxminded.UniversityCms.UniversityCmsApplication;
import ua.com.foxminded.UniversityCms.dao.teacher.TeacherRepository;
import ua.com.foxminded.UniversityCms.model.Teacher;
import ua.com.foxminded.UniversityCms.utils.TestConteinersConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@ContextConfiguration(classes = UniversityCmsApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class TeachersServiceImplTest {

    @MockBean
    private AppStarter appStarter;

    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void save_ShuldAddNewRowInTheTableTeschers_WhenCalled() {

        Teacher addTeacher = new Teacher();
        addTeacher.setId(1L);
        addTeacher.setFirstName("Kiril");
        addTeacher.setLastName("Sergeev");
        addTeacher.setPhone("0976962128");

        assertThat(teacherRepository.findById(1L)).isNotNull();
    }

    @Test
    @Sql("classpath:addTeachers.sql")
    void findAll_ShuldReturnListOfAllTeachers_WhenCalled() {
        List<Teacher> expectedResult = new ArrayList<>();
        Teacher addTeacher = new Teacher();
        addTeacher.setId(1L);
        addTeacher.setFirstName("Kiril");
        addTeacher.setLastName("Sergeev");
        addTeacher.setPhone("0976962128");
        expectedResult.add(addTeacher);
        addTeacher = new Teacher();
        addTeacher.setId(2L);
        addTeacher.setFirstName("Dmirtiy");
        addTeacher.setLastName("Samoilov");
        addTeacher.setPhone("0958868911");
        expectedResult.add(addTeacher);
        addTeacher = new Teacher();
        addTeacher.setId(3L);
        addTeacher.setFirstName("Broneslav");
        addTeacher.setLastName("Podgorniy");
        addTeacher.setPhone("0952684442");
        expectedResult.add(addTeacher);
        addTeacher = new Teacher();
        addTeacher.setId(4L);
        addTeacher.setFirstName("Vyacheslav");
        addTeacher.setLastName("Samoilov");
        addTeacher.setPhone("0978874731");
        expectedResult.add(addTeacher);

        List<Teacher> actualResult = teacherRepository.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method findAll from teachers");
    }

    @Test
    @Sql("classpath:addTeachers.sql")
    void delete_ShuldDeleteSelectedTeacherFromTable_WhenCalled() {

        Teacher deletedTeacher = new Teacher();
        deletedTeacher.setId(1L);
        deletedTeacher.setFirstName("Kiril");
        deletedTeacher.setLastName("Sergeev");
        deletedTeacher.setPhone("0976962128");

        teacherRepository.delete(deletedTeacher);

        assertThat(teacherRepository.findById(1L)).isEmpty();
    }

    @Test
    @Sql("classpath:addTeachers.sql")
    void deleteAll_ShuldDeleteAllRowsInTeachersTable_WhenCalled() {
        teacherRepository.deleteAll();

        assertThat(teacherRepository.findAll()).isEmpty();
    }

}
