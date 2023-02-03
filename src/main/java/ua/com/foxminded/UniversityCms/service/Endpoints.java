package ua.com.foxminded.UniversityCms.service;

import java.util.ArrayList;
import java.util.List;

public enum Endpoints {
    HOME("/"),
    LOGIN("/login"),
    LOGOUT("/logout"),
    ADMIN_VIEW("/admin"),
    SUBJECTS_CREATE("/subjects/create"),
    SUBJECTS_UPDATE("/subjects/update"),
    SUBJECTS_DELETE("/subjects/delete"),
    STUDENT_UPDATE_VIEW("/updateSubject"),
    SUBJECTS_DELETE_VIEW("/deleteSubject"),
    COURSE_CREATE_VIEW("/createCourse"),
    GROUPS_CREATE("/groups/create"),
    GROUPS_UPDATE("/groups/update"),
    GROUPS_DELETE("/groups/delete"),
    GROUP_UPDATE_VIEW("/updateGroup"),
    GROUP_DELETE_VIEW("/deleteGroup"),
    GROUP_CREATE_VIEW("/createGroup"),
    STUDENTS_GROUPS_ASSIGN("/studentsGroups/assign"),
    STUDENTS_GROUPS_REASSIGN("/studentsGroups/reassign"),
    STUDENTS_ASSIGN_VIEW("/assignStudents"),
    STUDENTS_REASSIGN_VIEW("/reassignStudents"),
    TIMETABLE_VIEW("/timetable/get"),
    TIMETABLE_CREATE_VIEW("/createTimetable"),
    TIMETABLE_UPDATE_VIEW("/updateTimetable"),
    TIMETABLE_DELETE_VIEW("/deleteTimetable"),
    TIMETABLE_CREATE("/timetable/create"),
    TIMETABLE_UPDATE("/timetable/update"),
    TIMETABLE_DELETE("/timetable/delete")
    ;

    private String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    static public List<Endpoints> getEndpointForAllUsers() {
        List<Endpoints> endpoints = new ArrayList<>();
        endpoints.add(HOME);
        endpoints.add(LOGIN);
        endpoints.add(LOGOUT);
        return endpoints;
    }

    static public List<Endpoints> getEndpointForAdminStuff() {
        List<Endpoints> endpoints = new ArrayList<>();
        endpoints.add(ADMIN_VIEW);
        endpoints.add(SUBJECTS_CREATE);
        endpoints.add(SUBJECTS_UPDATE);
        endpoints.add(SUBJECTS_DELETE);
        endpoints.add(STUDENT_UPDATE_VIEW);
        endpoints.add(SUBJECTS_DELETE_VIEW);
        endpoints.add(COURSE_CREATE_VIEW);
        endpoints.add(GROUPS_CREATE);
        endpoints.add(GROUPS_UPDATE);
        endpoints.add(GROUPS_DELETE);
        endpoints.add(GROUP_UPDATE_VIEW);
        endpoints.add(GROUP_DELETE_VIEW);
        endpoints.add(GROUP_CREATE_VIEW);
        endpoints.add(STUDENTS_GROUPS_ASSIGN);
        endpoints.add(STUDENTS_GROUPS_REASSIGN);
        endpoints.add(STUDENTS_ASSIGN_VIEW);
        endpoints.add(STUDENTS_REASSIGN_VIEW);
        endpoints.add(TIMETABLE_VIEW);
        endpoints.add(TIMETABLE_CREATE_VIEW);
        endpoints.add(TIMETABLE_UPDATE_VIEW);
        endpoints.add(TIMETABLE_DELETE_VIEW);
        endpoints.add(TIMETABLE_CREATE);
        endpoints.add(TIMETABLE_UPDATE);
        endpoints.add(TIMETABLE_DELETE);
        return endpoints;
    }

}
