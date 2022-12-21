package ua.com.foxminded.UniversityCms.dao.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.UniversityCms.model.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable,Long> {
}
