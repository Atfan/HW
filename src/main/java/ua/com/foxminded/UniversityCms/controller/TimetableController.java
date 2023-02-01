package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.UniversityCms.model.*;
import ua.com.foxminded.UniversityCms.service.groupservice.GroupService;
import ua.com.foxminded.UniversityCms.service.subjectservice.SubjectService;
import ua.com.foxminded.UniversityCms.service.teacherservice.TeachersService;
import ua.com.foxminded.UniversityCms.service.timetableservice.TimetableService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TeachersService teachersService;

    @RequestMapping(value = "/timetable/get", method = RequestMethod.GET)
    public String getTimetables(Model model) {
        model.addAttribute("timetables", timetableService.findAll());
        return "timetablesView";
    }

    @RequestMapping(value = "/createTimetable", method = RequestMethod.GET)
    public String createTimetableView(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "createTimetable";
    }

    @RequestMapping(value = "/updateTimetable", method = RequestMethod.GET)
    public String updateTimetableView(Model model) {
        model.addAttribute("timetables", timetableService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "updateTimetable";
    }

    @RequestMapping(value = "/deleteTimetable", method = RequestMethod.GET)
    public String deleteTimetableView(Model model) {
        model.addAttribute("timetables", timetableService.findAll());
        return "deleteTimetable";
    }

    @RequestMapping(value = "/timetable/create", method = RequestMethod.POST)
    public String createTimetable(@RequestParam("date") String date,
                              @RequestParam("pairsNumber") String pairsNumber,
                              @RequestParam("auditory") String auditory,
                              @RequestParam("subjectName") String subjectName,
                              @RequestParam("groupName") String groupName,
                              @RequestParam("teacherId") String teacherId,
                              Model model) throws ParseException {
        Timetable addTimetable = new Timetable();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        addTimetable.setDateOfDay(simpleDateFormat.parse(date));
        addTimetable.setPairsNumber(Integer.parseInt(pairsNumber));
        addTimetable.setAuditory(auditory);

        List<Subject> findSubjects = subjectService.findBySubjectNameLike(subjectName);
        List<Group> findGroups = groupService.findByGroupNameLike(groupName);
        Optional<Teacher> findTeacher = teachersService.findById(Long.parseLong(teacherId));

        if (findSubjects.size() != 0 && findGroups.size() != 0) {
            Optional<Subject> addSubject = Optional.ofNullable(findSubjects.get(0));
            Optional<Group> addGroup = Optional.ofNullable(findGroups.get(0));

            if (addSubject.isPresent() && addGroup.isPresent() && findTeacher.isPresent()) {

                addTimetable.setSubjectId(addSubject.get());
                addTimetable.setGroupId(addGroup.get());
                addTimetable.setTeacherId(findTeacher.get());

                timetableService.save(addTimetable);
            }
        }

        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        model.addAttribute("groups", groupService.findAll());

        return "createTimetable";
    }

    @RequestMapping(value = "/timetable/update", method = RequestMethod.POST)
    public String updateTimetable(@RequestParam("timetableId") String timetableId,
                              @RequestParam("date") String date,
                              @RequestParam("pairsNumber") String pairsNumber,
                              @RequestParam("auditory") String auditory,
                              @RequestParam("subjectName") String subjectName,
                              @RequestParam("groupName") String groupName,
                              @RequestParam("teacherId") String teacherId,
                              Model model) throws ParseException {
        Optional<Timetable> addTimetable = timetableService.findById(Long.parseLong(timetableId));

        if (addTimetable.isPresent()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            addTimetable.get().setDateOfDay(simpleDateFormat.parse(date));
            addTimetable.get().setPairsNumber(Integer.parseInt(pairsNumber));
            addTimetable.get().setAuditory(auditory);

            List<Subject> findSubjects = subjectService.findBySubjectNameLike(subjectName);
            List<Group> findGroups = groupService.findByGroupNameLike(groupName);
            Optional<Teacher> findTeacher = teachersService.findById(Long.parseLong(teacherId));

            if (findSubjects.size() != 0 && findGroups.size() != 0) {
                Optional<Subject> addSubject = Optional.ofNullable(findSubjects.get(0));
                Optional<Group> addGroup = Optional.ofNullable(findGroups.get(0));

                if (addSubject.isPresent() && addGroup.isPresent() && findTeacher.isPresent()) {

                    addTimetable.get().setSubjectId(addSubject.get());
                    addTimetable.get().setGroupId(addGroup.get());
                    addTimetable.get().setTeacherId(findTeacher.get());

                    timetableService.save(addTimetable.get());
                }
            }
        }
        model.addAttribute("timetables", timetableService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teachersService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "updateTimetable";
    }

    @RequestMapping(value = "/timetable/delete", method = RequestMethod.POST)
    public String deleteTimetable(@RequestParam("timetableId") String timetableId,
                                  Model model) {
        Optional<Timetable> deleteTimetable = timetableService.findById(Long.parseLong(timetableId));

        if (deleteTimetable.isPresent()) {
            timetableService.delete(deleteTimetable.get());
        }
        model.addAttribute("timetables", timetableService.findAll());
        return "deleteTimetable";
    }

}
