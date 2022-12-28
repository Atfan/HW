package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.foxminded.UniversityCms.service.groupservice.GroupService;
import ua.com.foxminded.UniversityCms.service.studentservice.StudentService;
import ua.com.foxminded.UniversityCms.service.subjectservice.SubjectService;
import ua.com.foxminded.UniversityCms.service.teacherservice.TeachersService;

@Controller
public class UniversityController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeachersService teachersService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String studentsPage(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String teachersPage(Model model) {
        model.addAttribute("teachers", teachersService.findAll());
        return "teachers";
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public String subjectsPage(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subjects";
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String groupsPage(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "groups";
    }

}
