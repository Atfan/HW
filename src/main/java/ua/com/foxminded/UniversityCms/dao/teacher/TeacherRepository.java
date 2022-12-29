package ua.com.foxminded.UniversityCms.dao.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.UniversityCms.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
