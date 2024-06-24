package org.company.TicketOnline2.service.appuserservice;

import org.company.ticketonline2.dao.user.UserRepository;
import org.company.ticketonline2.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

}
