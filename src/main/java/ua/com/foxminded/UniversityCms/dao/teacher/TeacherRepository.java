package ua.com.foxminded.UniversityCms.dao.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.UniversityCms.model.Teacher;

import java.util.List;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    List<Teacher> findByFirstNameAndLastNameLike(String firstName, String lastName);

}
