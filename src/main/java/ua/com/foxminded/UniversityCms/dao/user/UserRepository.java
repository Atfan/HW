package ua.com.foxminded.UniversityCms.dao.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.UniversityCms.model.AppUser;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query("from AppUser a where a.userName = ?1")
    Optional<AppUser> findUserAccount(String userName);
}