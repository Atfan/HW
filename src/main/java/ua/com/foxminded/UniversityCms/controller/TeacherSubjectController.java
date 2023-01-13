package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.UniversityCms.model.Subject;
import ua.com.foxminded.UniversityCms.model.Teacher;
import ua.com.foxminded.UniversityCms.service.subjectservice.SubjectService;
import ua.com.foxminded.UniversityCms.service.teacherservice.TeachersService;

import java.util.List;

@Controller
public class TeacherSubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeachersService teachersService;

    @GetMapping(value = "/assignTeacherToSubject")
    public String assignTeacherToSubjectPage(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        return "assignTeacherToSubject";
    }

    @GetMapping(value = "/reassignTeacherToSubject")
    public String reassignTeacherToSubjectPage(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        return "reassignTeacherToSubject";
    }

    @RequestMapping(value = "/subjectsTeachers/assign", method = RequestMethod.POST)
    public String assignSubjectToTeacher(@RequestParam("subjectName") String subjectName,
                                @RequestParam("teacherName") String teacherName,
                                Model model) {

        List<Subject> findSubject = subjectService.findBySubjectNameLike(subjectName);

        String[] findTeacherParametres = teacherName.split(" ");
        List<Teacher> findTeachers = teachersService.findByFirstNameAndLastNameLike(findTeacherParametres[0],
                                                        findTeacherParametres[1]);
        if (findSubject.size() != 0 && findTeachers.size() != 0 ) {
            Subject addSubject = findSubject.get(0);
            addSubject.setTeacher(findTeachers.get(0));
            subjectService.save(addSubject);
        }

        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        return "assignTeacherToSubject";
    }

    @RequestMapping(value = "/subjectsTeachers/reassign", method = RequestMethod.POST)
    public String reassignSubjectToTeacher(@RequestParam("subjectName") String subjectName,
                                @RequestParam("teacherName") String teacherName,
                                Model model) {

        List<Subject> findSubject = subjectService.findBySubjectNameLike(subjectName);

        String[] findTeacherParametres = teacherName.split(" ");
        List<Teacher> findTeachers = teachersService.findByFirstNameAndLastNameLike(findTeacherParametres[0],
                findTeacherParametres[1]);
        if (findSubject.size() != 0 && findTeachers.size() != 0 ) {
            Subject addSubject = findSubject.get(0);
            addSubject.setTeacher(findTeachers.get(0));
            subjectService.save(addSubject);
        }

        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        return "reassignTeacherToSubject";
    }

}
