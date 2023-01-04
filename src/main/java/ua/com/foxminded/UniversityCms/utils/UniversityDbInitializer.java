package ua.com.foxminded.UniversityCms.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.exception.FileException;
import ua.com.foxminded.UniversityCms.model.*;
import ua.com.foxminded.UniversityCms.service.appuserservice.AppUserService;
import ua.com.foxminded.UniversityCms.service.schoolinitservice.SchoolInitService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Service
public class UniversityDbInitializer {

    private final SchoolInitService schoolInitService;

    @Autowired
    private AppUserService appUserService;

    private final String CORPUS_NAME = "corpus";
    private final String AUDITORY_DELIMETER = "--";

    private static final Random RANDOM_GENERATOR = new Random();

    public UniversityDbInitializer(SchoolInitService schoolInitService) {
        this.schoolInitService = schoolInitService;
    }

    public void deleteAllRowsInDB() {
        schoolInitService.deleteAllRowsInDB();
        log.debug("All rows in DB was deleted");
    }

    public void createUsers() {
        AppUser apu1 = new AppUser();
        apu1.setUserId(1L);
        apu1.setUserName("admin");
        apu1.setEncrytedPassword("$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu");
        apu1.setEnabled(true);
        appUserService.save(apu1);

        AppUser apu2 = new AppUser();
        apu2.setUserId(2L);
        apu2.setUserName("user");
        apu2.setEncrytedPassword("$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu");
        apu2.setEnabled(true);
        appUserService.save(apu2);
    }

    public void createRandomGroups() {
        List<Group> groups = new ArrayList<>();
        for (int count = 0; count < 10; count++) {
            StringBuilder groupName = new StringBuilder();

            groupName.append((char)(RANDOM_GENERATOR.nextInt(26) + 'a'))
                    .append((char)(RANDOM_GENERATOR.nextInt(26) + 'a'))
                    .append("-")
                    .append((char)(RANDOM_GENERATOR.nextInt(10) + '0'))
                    .append((char)(RANDOM_GENERATOR.nextInt(10) + '0'));
            Group group = new Group();
            group.setGroupName(groupName.toString());
            group.setDescription("This is " + groupName + " group");
            groups.add(group);
        }
        schoolInitService.saveGroupList(groups);
        log.debug("Random groups are created");
    }

    public void createSubjects() throws FileException {
        String subjectsFileName = PropertyFactory.getInstance().getProperty().getProperty("data.subjects");
        List<Teacher> teachers = schoolInitService.findAllTeachers();

        List<Subject> subjects = new ArrayList<>();
        try (Stream<String> lineStream = Files.lines(Paths.get(subjectsFileName))) {
            for (var currentString : lineStream.collect(Collectors.toList())) {
                Subject subject = new Subject();
                subject.setSubjectName(currentString);
                subject.setTeacher(teachers.get(RANDOM_GENERATOR.nextInt(teachers.size())));

                subjects.add(subject);
            }
            schoolInitService.saveSubjectsList(subjects);
            log.debug("Random courses are created");
        } catch (IOException exception) {
            log.error("Error open source courses file");
            throw new FileException("Error with source courses file");
        }
    }

    public void createRandomStudents() throws FileException {
        TxtFileReader txtFileReaderNames = new TxtFileReader("data.names");
        List<String> randomNames = txtFileReaderNames.readFile();
        TxtFileReader txtFileReaderLastNames = new TxtFileReader("data.lastnames");
        List<String> randomLastNames = txtFileReaderLastNames.readFile();

        List<Group> groups = schoolInitService.findAllGroups();

        List<Student> studentsToAdd = new ArrayList<>();
        for (int count = 0; count < 200; count++) {
            Student addStudent = new Student();
            addStudent.setFirstName(randomNames.get(RANDOM_GENERATOR.nextInt(randomNames.size())));
            addStudent.setLastName(randomLastNames.get(RANDOM_GENERATOR.nextInt(randomLastNames.size())));
            addStudent.setGroup(groups.get(RANDOM_GENERATOR.nextInt(groups.size())));
            addStudent.setStudTicketNumber(createRandomStudTicketNumber());
            addStudent.setPhone(createRandomPhone());
            studentsToAdd.add(addStudent);
        }
        schoolInitService.saveStudentsList(studentsToAdd);
        log.debug("Random students are created");
    }

    public void createRandomTeachers() throws FileException {
        TxtFileReader txtFileReaderNames = new TxtFileReader("data.names");
        List<String> randomNames = txtFileReaderNames.readFile();
        TxtFileReader txtFileReaderLastNames = new TxtFileReader("data.lastnames");
        List<String> randomLastNames = txtFileReaderLastNames.readFile();

        List<Teacher> teachersToAdd = new ArrayList<>();
        for (int count = 0; count < 10; count++) {
            Teacher addTeacher = new Teacher();
            addTeacher.setFirstName(randomNames.get(RANDOM_GENERATOR.nextInt(randomNames.size())));
            addTeacher.setLastName(randomLastNames.get(RANDOM_GENERATOR.nextInt(randomLastNames.size())));
            addTeacher.setPhone(createRandomPhone());
            teachersToAdd.add(addTeacher);
        }
        schoolInitService.saveTeachersList(teachersToAdd);
        log.debug("Random students are created");
    }

    public void createRandomTimetable() {

        List<Group> groups = schoolInitService.findAllGroups();
        List<Teacher> teachers = schoolInitService.findAllTeachers();
        List<Subject> subject = schoolInitService.findAllSubjects();
        List<String> auditiries = createRandomListOfAuditory();

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, 1);

        List<Timetable> timetables = new ArrayList<>();
        for (int counterIter = 0; counterIter<30; counterIter++) {

            for (int currentPair = 1; currentPair<5; currentPair++) {
                Timetable timetable = new Timetable();
                timetable.setDateOfDay(calendar.getTime());
                timetable.setPairsNumber(currentPair);
                timetable.setGroupId(groups.get(RANDOM_GENERATOR.nextInt(groups.size())));
                timetable.setTeacherId(teachers.get(RANDOM_GENERATOR.nextInt(teachers.size())));
                timetable.setSubjectId(subject.get(RANDOM_GENERATOR.nextInt(subject.size())));
                timetable.setAuditory(auditiries.get(RANDOM_GENERATOR.nextInt(auditiries.size())));
                timetables.add(timetable);
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        schoolInitService.saveTimetablesList(timetables);
        log.debug("Random timetables are created");
    }

    private List<String> createRandomListOfAuditory() {
        List<String> auditories = new ArrayList<>();
        for (int counterAuditory = 0; counterAuditory<20; counterAuditory++) {
            String currentAuditory = createRandomAuditory();
            auditories.add(currentAuditory);
        }
        return auditories;
    }

    private String createRandomAuditory() {
        StringBuilder auditory = new StringBuilder();
        auditory.append(CORPUS_NAME)
                .append(RANDOM_GENERATOR.nextInt(5) + 1)
                .append(AUDITORY_DELIMETER)
                .append(RANDOM_GENERATOR.nextInt(9) + 1)
                .append(RANDOM_GENERATOR.nextInt(9) + 1)
                .append(RANDOM_GENERATOR.nextInt(9) + 1);
        return auditory.toString();
    }

    private String createRandomPhone() {
        PhoneOperatorsCode[] codes =  PhoneOperatorsCode.values();

        StringBuilder phone = new StringBuilder();
        phone.append(codes[RANDOM_GENERATOR.nextInt(codes.length)].getOperatorsCode());

        for (int counter = 0; counter < 7; counter++) {
            phone.append(RANDOM_GENERATOR.nextInt(9) + 1);
        }
        return phone.toString();
    }

    private int createRandomStudTicketNumber() {
        int studTicketNumber = 0;

        for (int countnumber = 0; countnumber< 8; countnumber++) {
            studTicketNumber = studTicketNumber*10 + (RANDOM_GENERATOR.nextInt(9) + 1);
        }
        return studTicketNumber;
    }

}
