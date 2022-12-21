package ua.com.foxminded.UniversityCms.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.exception.FileException;

@Log4j2
@Service
public class UniversityInitializer {

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
        } catch (FileException e) {
            log.error("error with source file names");
        }
    }
}
