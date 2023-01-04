package ua.com.foxminded.UniversityCms.dao.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.UniversityCms.model.Group;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group,Long> {
}
