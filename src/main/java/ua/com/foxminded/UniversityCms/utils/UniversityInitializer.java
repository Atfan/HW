package ua.com.foxminded.UniversityCms.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.exception.FileException;
import ua.com.foxminded.UniversityCms.model.Student;
import ua.com.foxminded.UniversityCms.model.Timetable;
import ua.com.foxminded.UniversityCms.service.studentservice.StudentService;
import ua.com.foxminded.UniversityCms.service.timetableservice.TimetableService;

import java.util.List;

@Log4j2
@Service
public class UniversityInitializer {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TimetableService timetableService;

    private final UniversityDbInitializer schoolDbInitializer;

    public UniversityInitializer(UniversityDbInitializer schoolDbInitializer) {
        this.schoolDbInitializer = schoolDbInitializer;
    }

    public void schoolInitialize() {
        try {
            log.info("Start university initialize");
            schoolDbInitializer.deleteAllRowsInDB();
            schoolDbInitializer.createRandomTeachers();
            schoolDbInitializer.createRandomGroups();
            schoolDbInitializer.createSubjects();
            schoolDbInitializer.createRandomStudents();
            schoolDbInitializer.createRandomTimetable();
            log.info("End university initialize");

            List<Student> students = studentService.findAll();
            List<Timetable> timetables = timetableService.findDayTimetableForStudent(students.get(0).getId());
            System.out.println(timetables.toString());

        } catch (FileException e) {
            log.error("error with source file names");
        }
    }
}
