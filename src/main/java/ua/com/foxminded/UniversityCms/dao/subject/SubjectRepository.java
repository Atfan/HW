package ua.com.foxminded.UniversityCms.dao.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.UniversityCms.model.Subject;

@Repository
@Transactional
public interface SubjectRepository extends JpaRepository<Subject,Long> {

}
