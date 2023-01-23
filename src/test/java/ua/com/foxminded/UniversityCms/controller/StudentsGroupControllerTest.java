package ua.com.foxminded.UniversityCms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.foxminded.UniversityCms.model.Group;
import ua.com.foxminded.UniversityCms.model.Student;
import ua.com.foxminded.UniversityCms.service.groupservice.GroupService;
import ua.com.foxminded.UniversityCms.service.studentservice.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentsGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private StudentService studentService;

    @MockBean
    private GroupService groupService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetListOfStudentsInCurrentGroup_ShouldReturnListOfStudents_WhenRequestSendFromAll() throws Exception {

        List<Student> students = new ArrayList<>();

        Group addGroup = new Group();
        addGroup.setId(1L);
        addGroup.setGroupName("as-12");
        addGroup.setDescription("This is as-12 group");

        List<Group> groups = new ArrayList<>();
        groups.add(addGroup);

        Student addStudent = new Student();
        addStudent.setId(1L);
        addStudent.setFirstName("Pavel");
        addStudent.setLastName("Kadancev");
        addStudent.setPhone("0508988112");
        addStudent.setStudTicketNumber(68381951);
        addStudent.setGroup(addGroup);
        students.add(addStudent);

        addStudent = new Student();
        addStudent.setId(2L);
        addStudent.setFirstName("Aleksey");
        addStudent.setLastName("Sviridov");
        addStudent.setPhone("0979258973");
        addStudent.setStudTicketNumber(31871352);
        addStudent.setGroup(addGroup);
        students.add(addStudent);

        when(studentService.findByIdLike(1L)).thenReturn(students);
        when(groupService.findAll()).thenReturn(groups);

        mockMvc.perform(get("/studentsInGroup").with(user("admin").password("123").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("students", studentService.findByIdLike(1L)))
                .andExpect(model().attribute("groups", groupService.findAll()))
                .andExpect(view().name("studentsInCurrentGroup"));

    }

    @Test
    public void testGetListOfStudentsInCurrentGroup_ShouldReturnOK_WhenRequestSendFromUser() throws Exception {

        List<Student> students = new ArrayList<>();

        Group addGroup = new Group();
        addGroup.setId(1L);
        addGroup.setGroupName("as-12");
        addGroup.setDescription("This is as-12 group");

        List<Group> groups = new ArrayList<>();
        groups.add(addGroup);

        Student addStudent = new Student();
        addStudent.setId(1L);
        addStudent.setFirstName("Pavel");
        addStudent.setLastName("Kadancev");
        addStudent.setPhone("0508988112");
        addStudent.setStudTicketNumber(68381951);
        addStudent.setGroup(addGroup);
        students.add(addStudent);

        addStudent = new Student();
        addStudent.setId(2L);
        addStudent.setFirstName("Aleksey");
        addStudent.setLastName("Sviridov");
        addStudent.setPhone("0979258973");
        addStudent.setStudTicketNumber(31871352);
        addStudent.setGroup(addGroup);
        students.add(addStudent);

        when(studentService.findByIdLike(1L)).thenReturn(students);
        when(groupService.findAll()).thenReturn(groups);

        mockMvc.perform(get("/studentsInGroup").with(user("user").password("123").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("students", studentService.findByIdLike(1L)))
                .andExpect(model().attribute("groups", groupService.findAll()))
                .andExpect(view().name("studentsInCurrentGroup"));

    }

    @Test
    public void testGetListOfStudentsInCurrentGroup_ShouldReturnOK_WhenRequestSendFromStuff() throws Exception {

        List<Student> students = new ArrayList<>();

        Group addGroup = new Group();
        addGroup.setId(1L);
        addGroup.setGroupName("as-12");
        addGroup.setDescription("This is as-12 group");

        List<Group> groups = new ArrayList<>();
        groups.add(addGroup);

        Student addStudent = new Student();
        addStudent.setId(1L);
        addStudent.setFirstName("Pavel");
        addStudent.setLastName("Kadancev");
        addStudent.setPhone("0508988112");
        addStudent.setStudTicketNumber(68381951);
        addStudent.setGroup(addGroup);
        students.add(addStudent);

        addStudent = new Student();
        addStudent.setId(2L);
        addStudent.setFirstName("Aleksey");
        addStudent.setLastName("Sviridov");
        addStudent.setPhone("0979258973");
        addStudent.setStudTicketNumber(31871352);
        addStudent.setGroup(addGroup);
        students.add(addStudent);

        when(studentService.findByIdLike(1L)).thenReturn(students);
        when(groupService.findAll()).thenReturn(groups);

        mockMvc.perform(get("/studentsInGroup").with(user("stuff").password("123").roles("STUFF")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("students", studentService.findByIdLike(1L)))
                .andExpect(model().attribute("groups", groupService.findAll()))
                .andExpect(view().name("studentsInCurrentGroup"));
    }

    @WithMockUser(username="user", password="123",roles="USER")
    @Test
    public void testAssignStudentsToGroupPage_ShouldReturnError4xx_WhenRequestSendFromUser() throws Exception {

        mockMvc.perform(post("/studentsGroups/assign")
                .param("studentName","Student Student")
                .param("groupName","gr-11"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testAssignStudentsToGroupPage_ShouldReturnError4xx_WhenRequestSendFromStudent() throws Exception {

        mockMvc.perform(post("/studentsGroups/assign").with(user("student").password("123").roles("STUDENT"))
                .param("studentName","Student Student")
                .param("groupName","gr-11"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testAssignStudentsToGroupPage_ShouldReturnOK_WhenRequestSendFromAdmin() throws Exception {

        mockMvc.perform(post("/studentsGroups/assign")
                    .with(user("admin").password("123").roles("ADMIN"))
                    .param("studentName","Student Student")
                    .param("groupName","gr-11"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReassignStudentsToGroupPage_ShouldReturnOK_WhenRequestSendFromAdmin() throws Exception {

        mockMvc.perform(post("/studentsGroups/reassign").with(user("admin").password("123").roles("ADMIN"))
                .param("studentName","Student Student")
                .param("groupName","gr-11"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReassignStudentsToGroupPage_ShouldReturnError4xx_WhenRequestSendFromStudent() throws Exception {

        mockMvc.perform(post("/studentsGroups/reassign").with(user("student").password("123").roles("STUDENT"))
                .param("studentName","Student Student")
                .param("groupName","gr-11"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="student", password="123",roles="STUDENT")
    @Test
    public void testReassignStudentsToGroupPageWithMockUser_ShouldReturnError4xx_WhenRequestSendFromStudent() throws Exception {

        mockMvc.perform(post("/studentsGroups/reassign")
                .param("studentName","Student Student")
                .param("groupName","gr-11"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="teacher", password="123",roles="TEACHER")
    @Test
    public void testReassignStudentsToGroupPageWithMockUser_ShouldReturnError4xx_WhenRequestSendFromTeacher() throws Exception {

        mockMvc.perform(post("/studentsGroups/reassign")
                .param("studentName","Student Student")
                .param("groupName","gr-11"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="admin", password="123",roles="ADMIN")
    @Test
    public void testAssignStudentsToGroupPageWithMockUser_ShouldReturnOK_WhenRequestSendFromAdmin() throws Exception {

        mockMvc.perform(post("/studentsGroups/assign")
                .param("studentName","Student Student")
                .param("groupName","gr-11"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username="stuff", password="123",roles="STUFF")
    @Test
    public void testAssignStudentsToGroupPageWithMockUser_ShouldReturnOK_WhenRequestSendFromStuff() throws Exception {

        mockMvc.perform(post("/studentsGroups/assign")
                .param("studentName","Student Student")
                .param("groupName","gr-11"))
                .andExpect(status().isOk());
    }

}
