package ua.com.foxminded.UniversityCms.dao.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.UniversityCms.model.Timetable;

import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public interface TimetableRepository extends JpaRepository<Timetable,Long> {

    @Query(value =
            "SELECT ttable.* FROM timetable ttable join groups gr on ttable.group_id = gr.id "
            + "  join students stud on gr.id = stud.group_id "
            + " WHERE stud.id = :findStud ", nativeQuery = true)
    List<Timetable> findDayTimetableForStudent(@Param("findStud") long id) throws SQLException;

}
