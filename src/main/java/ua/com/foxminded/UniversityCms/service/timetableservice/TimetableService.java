package ua.com.foxminded.UniversityCms.service.timetableservice;

import ua.com.foxminded.UniversityCms.model.Timetable;

import java.util.List;

public interface TimetableService {
    void save(Timetable timetable) ;

    int[] saveStudentsList(List<Timetable> timetables) ;

    void update(Timetable timetable) ;

    void delete(Timetable timetable) ;

    List<Timetable> findAll() ;

    void deleteAll() ;

    List<Timetable> findDayTimetableForStudent(long id);
}
