package ua.com.foxminded.UniversityCms.dao.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.UniversityCms.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    String FIND_ALL_STUDENTS_IN_CURRENT_GROUP =
            """
            SELECT students_.*
            FROM groups groups_ JOIN Students students_ ON groups_.id = students_.group_id
            WHERE groups_.id = :groupId\s""";

    List<Student> findByFirstNameAndLastNameLike(String firstName, String lastName);

    @Query(value = FIND_ALL_STUDENTS_IN_CURRENT_GROUP, nativeQuery = true)
    List<Student> findByIdLike(@Param("groupId") Long id);

}
