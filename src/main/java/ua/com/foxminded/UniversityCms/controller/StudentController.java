package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.foxminded.UniversityCms.service.studentservice.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students/get", method = RequestMethod.GET)
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @RequestMapping(value = "/students/update", method = RequestMethod.POST)
    public String updateStudent(Model model) {
        return null;
    }

    @RequestMapping(value = "/students/create", method = RequestMethod.POST)
    public String createStudent(Model model) {
        return null;
    }

    @RequestMapping(value = "/students/delete", method = RequestMethod.POST)
    public String deleteStudent(Model model) {
        return null;
    }
}
