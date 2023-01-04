package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.foxminded.UniversityCms.service.subjectservice.SubjectService;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/subjects/get", method = RequestMethod.GET)
    public String getSubjects(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subjects";
    }

    @RequestMapping(value = "/subjects/update", method = RequestMethod.POST)
    public String updateSubject(Model model) {
        return null;
    }

    @RequestMapping(value = "/subjects/create", method = RequestMethod.POST)
    public String createSubject(Model model) {
        return null;
    }

    @RequestMapping(value = "/subjects/delete", method = RequestMethod.POST)
    public String deleteSubject(Model model) {
        return null;
    }
}
