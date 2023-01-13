package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.UniversityCms.model.Subject;
import ua.com.foxminded.UniversityCms.service.subjectservice.SubjectService;

import java.util.List;

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
    public String updateSubject(@RequestParam("subjectName") String subjectName,
                                @RequestParam("updateSubjectName") String updateSubjectName,
                                Model model)
    {
        List<Subject> findSubjects = subjectService.findBySubjectNameLike(subjectName);
        if (findSubjects.size() != 0) {
            Subject updateSubject = findSubjects.get(0);
            updateSubject.setSubjectName(updateSubjectName);
            subjectService.save(updateSubject);
        }
        model.addAttribute("subjects", subjectService.findAll());
        return "updateCourse";
    }

    @RequestMapping(value = "/subjects/create", method = RequestMethod.POST)
    public String createSubject(@RequestParam("subjectName") String courseName, Model model) {

        Subject addSubject = new Subject();
        addSubject.setSubjectName(courseName);
        subjectService.save(addSubject);
        return "createCourse";
    }

    @RequestMapping(value = "/subjects/delete", method = RequestMethod.POST)
    public String deleteSubject(@RequestParam("subjectName") String subjectName,Model model) {

        List<Subject> findSubjects = subjectService.findBySubjectNameLike(subjectName);
        if (findSubjects.size() != 0) {
            Subject deleteSubject = findSubjects.get(0);
            subjectService.delete(deleteSubject);
        }
        model.addAttribute("subjects", subjectService.findAll());
        return "deleteCourse";
    }

    @GetMapping(value = "/updateSubject")
    public String updateCoursePage(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "updateCourse";
    }

    @GetMapping(value = "/deleteSubject")
    public String deleteCoursePage(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "deleteCourse";
    }

}
