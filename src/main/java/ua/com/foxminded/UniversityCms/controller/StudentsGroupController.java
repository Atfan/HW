package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.UniversityCms.model.Group;
import ua.com.foxminded.UniversityCms.model.Student;
import ua.com.foxminded.UniversityCms.service.groupservice.GroupService;
import ua.com.foxminded.UniversityCms.service.studentservice.StudentService;

import java.util.List;

@Controller
public class StudentsGroupController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private  GroupService groupService;

    @GetMapping(value = "/assignStudentsToGroup")
    public String assignStudentsToGroupPage(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "assignStudentsToGroup";
    }

    @GetMapping(value = "/reassignStudentsToGroup")
    public String reassignStudentsToGroupPage(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "reassignStudentsToGroup";
    }

    @GetMapping(value = "/studentsInGroup")
    public String listStudentsInGroupPage(Model model) {

        List<Group> groups = groupService.findAll();
        model.addAttribute("students", studentService.findByIdLike(groups.get(0).getId()));
        model.addAttribute("groups", groupService.findAll());
        return "studentsInCurrentGroup";
    }

    @RequestMapping(value = "/studentsGroups/listStudentsInGroup", method = RequestMethod.POST)
    public String getListStudentsInGroup(@RequestParam("groupId") Long groupId,
                                         Model model) {

        model.addAttribute("students", studentService.findByIdLike(groupId));
        model.addAttribute("groups", groupService.findAll());
        return "studentsInCurrentGroup";
    }

    @RequestMapping(value = "/studentsGroups/assign", method = RequestMethod.POST)
    public String assignSubjectToTeacher(@RequestParam("studentName") String studentName,
                                         @RequestParam("groupName") String groupName,
                                         Model model) {

        List<Group> findGroup = groupService.findByGroupNameLike(groupName);

        String[] findStudentParametres = studentName.split(" ");
        List<Student> findStudents = studentService.findByFirstNameAndLastNameLike(findStudentParametres[0],
                findStudentParametres[1]);
        if (findGroup.size() != 0 && findStudents.size() != 0 ) {
            Student addStudent = findStudents.get(0);
            addStudent.setGroup(findGroup.get(0));
            studentService.save(addStudent);
        }

        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "reassignStudentsToGroup";
    }

    @RequestMapping(value = "/studentsGroups/reassign", method = RequestMethod.POST)
    public String reassignSubjectToTeacher(@RequestParam("studentName") String studentName,
                                           @RequestParam("groupName") String groupName,
                                           Model model) {

        List<Group> findGroup = groupService.findByGroupNameLike(groupName);

        String[] findStudentParametres = studentName.split(" ");
        List<Student> findStudents = studentService.findByFirstNameAndLastNameLike(findStudentParametres[0],
                findStudentParametres[1]);
        if (findGroup.size() != 0 && findStudents.size() != 0 ) {
            Student addStudent = findStudents.get(0);
            addStudent.setGroup(findGroup.get(0));
            studentService.save(addStudent);
        }

        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "reassignStudentsToGroup";
    }

}
