package org.company.ticketonline2.dao.user;


import org.company.ticketonline2.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query("from AppUser a where a.userName = ?1")
    Optional<AppUser> findUserAccount(String userName);

}