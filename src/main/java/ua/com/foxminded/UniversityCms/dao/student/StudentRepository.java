package ua.com.foxminded.UniversityCms.dao.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.UniversityCms.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
