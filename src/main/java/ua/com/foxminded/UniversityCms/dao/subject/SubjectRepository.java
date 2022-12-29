package ua.com.foxminded.UniversityCms.dao.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.UniversityCms.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
