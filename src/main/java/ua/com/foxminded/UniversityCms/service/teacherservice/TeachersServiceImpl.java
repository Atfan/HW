package ua.com.foxminded.UniversityCms.service.teacherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.dao.teacher.TeacherRepository;
import ua.com.foxminded.UniversityCms.model.Teacher;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Teacher> findByFirstNameAndLastNameLike(String firstName, String lastName) {
        return teacherRepository.findByFirstNameAndLastNameLike(firstName,lastName);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

}
