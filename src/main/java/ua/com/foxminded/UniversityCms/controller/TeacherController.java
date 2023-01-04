package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.foxminded.UniversityCms.service.teacherservice.TeachersService;

@Controller
public class TeacherController {

    @Autowired
    private TeachersService teachersService;

    @RequestMapping(value = "/teachers/get", method = RequestMethod.GET)
    public String getTeachers(Model model) {
        model.addAttribute("teachers", teachersService.findAll());
        return "teachers";
    }

    @RequestMapping(value = "/teachers/update", method = RequestMethod.POST)
    public String updateTeacher(Model model) {
        return null;
    }

    @RequestMapping(value = "/teachers/create", method = RequestMethod.POST)
    public String createTeacher(Model model) {
        return null;
    }

    @RequestMapping(value = "/teachers/delete", method = RequestMethod.POST)
    public String deleteTeacher(Model model) {
        return null;
    }
}
