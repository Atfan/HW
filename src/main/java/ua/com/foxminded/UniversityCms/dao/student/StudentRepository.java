package ua.com.foxminded.UniversityCms.dao.student;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.UniversityCms.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
