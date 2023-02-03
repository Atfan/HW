package ua.com.foxminded.UniversityCms.service.timetableservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.dao.timetable.TimetableRepository;
import ua.com.foxminded.UniversityCms.model.Timetable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimetableServiceImpl implements TimetableService{

    @Autowired
    private TimetableRepository timetableRepository;


    @Override
    public void save(Timetable timetable) {
        timetableRepository.save(timetable);
    }

    @Override
    public int[] saveStudentsList(List<Timetable> timetables) {
        timetableRepository.saveAll(timetables);
        return new int[0];
    }

    @Override
    public void update(Timetable timetable) {
        timetableRepository.save(timetable);
    }

    @Override
    public void delete(Timetable timetable) {
        timetableRepository.delete(timetable);
    }

    @Override
    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    @Override
    public void deleteAll() {
        timetableRepository.deleteAll();
    }

    @Override
    public List<Timetable> findDayTimetableForStudent(long id) {
        try {
            return timetableRepository.findDayTimetableForStudent(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Timetable> findById(Long id) {
        return timetableRepository.findById(id);
    }

}
