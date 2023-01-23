package ua.com.foxminded.UniversityCms.dao.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.UniversityCms.model.Subject;

import java.util.List;

@Repository
@Transactional
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    String FIND_ALL_SUBJECTS_FROM_CURRENT_TEACHER =
            """
            SELECT subjects_.*
            FROM Teachers teachers_ JOIN Subjects subjects_ ON teachers_.id = subjects_.teacher_id
            WHERE teachers_.id = :teacherId\s""";

    List<Subject> findBySubjectNameLike(String subjectName);

    @Query(value = FIND_ALL_SUBJECTS_FROM_CURRENT_TEACHER, nativeQuery = true)
    List<Subject> findByIdLike(@Param("teacherId") Long id);

}
