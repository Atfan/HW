package ua.com.foxminded.UniversityCms.service.appuserservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.dao.user.UserRepository;
import ua.com.foxminded.UniversityCms.model.AppUser;

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
