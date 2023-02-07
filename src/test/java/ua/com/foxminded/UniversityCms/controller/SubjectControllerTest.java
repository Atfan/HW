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
import ua.com.foxminded.UniversityCms.model.Subject;
import ua.com.foxminded.UniversityCms.model.Teacher;
import ua.com.foxminded.UniversityCms.service.subjectservice.SubjectService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private SubjectService subjectService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetSubjectsPage_ShouldReturnListOfSubjects_WhenRequestSendFromAdmin() throws Exception {

        Teacher addTeacher = new Teacher();
        addTeacher.setId(1L);
        addTeacher.setPhone("0959336993");
        addTeacher.setFirstName("Egor");
        addTeacher.setLastName("Zagudaev");

        List<Subject> subjects = new ArrayList<>();
        Subject addSubject = new Subject();
        addSubject.setId(1L);
        addSubject.setSubjectName("mathematics");
        addSubject.setTeacher(addTeacher);
        subjects.add(addSubject);

        addSubject = new Subject();
        addSubject.setId(2L);
        addSubject.setSubjectName("biology");
        addSubject.setTeacher(addTeacher);
        subjects.add(addSubject);

        when(subjectService.findAll()).thenReturn(subjects);

        mockMvc.perform(get("/subjects/get").with(user("admin").password("123").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("subjects", subjectService.findAll()))
                .andExpect(view().name("subjects"));
    }

    @Test
    public void testGetSubjectsPage_ShouldReturnError4xx_WhenRequestSendFromUser() throws Exception {

        mockMvc.perform(get("/subjects/get").with(user("user").password("123").roles("USER")))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="anonim", password="123",roles="DEFAULT")
    @Test
    public void testBriefSubjectsPage_ShouldReturnOK_WhenRequestSendFromAnonim() throws Exception {

        mockMvc.perform(get("/briefsubjects"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username="admin", password="123",roles="ADMIN")
    @Test
    public void testBriefSubjectsPage_ShouldReturnError4xx_WhenRequestSendFromAdmin() throws Exception {

        mockMvc.perform(get("/briefsubjects"))
                .andExpect(status().is4xxClientError());
    }

}
