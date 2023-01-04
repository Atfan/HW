package ua.com.foxminded.UniversityCms.service.teacherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.dao.teacher.TeacherRepository;
import ua.com.foxminded.UniversityCms.model.Teacher;

import java.util.List;

@Service
public class TeachersServiceImpl implements TeachersService{
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public int[] saveStudentsList(List<Teacher> teachers) {
        teacherRepository.saveAll(teachers);
        return new int[0];
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteAll() {
        teacherRepository.deleteAll();
    }

}
