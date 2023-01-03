package ua.com.foxminded.UniversityCms.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.foxminded.UniversityCms.utils.WebUtils;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/welcome"})
    public String welcomePage(Model model) {
        return "loginPage";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.pageInfoOutputMessageCreator(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {

        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.pageInfoOutputMessageCreator(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

}
