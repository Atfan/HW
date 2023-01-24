package ua.com.foxminded.UniversityCms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherSubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(username="teacher", password="123",roles="TEACHER")
    @Test
    public void testGetSubjectsFromTeacherPage_ShouldReturnOk_WhenRequestSendFromTeacher() throws Exception {

        mockMvc.perform(get("/teachersSubjects"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username="admin", password="123",roles="ADMIN")
    @Test
    public void testGetSubjectsFromTeacherPage_ShouldReturnError4xx_WhenRequestSendFromAdmin() throws Exception {

        mockMvc.perform(get("/teachersSubjects"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="stuff", password="123",roles="STUFF")
    @Test
    public void testGetSubjectsFromTeacherPage_ShouldReturnError4xx_WhenRequestSendFromStuff() throws Exception {

        mockMvc.perform(get("/teachersSubjects"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="student", password="123",roles="STUDENT")
    @Test
    public void testGetSubjectsFromTeacherPage_ShouldReturnError4xx_WhenRequestSendFromStudent() throws Exception {

        mockMvc.perform(get("/teachersSubjects"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="teacher", password="123",roles="TEACHER")
    @Test
    public void testChoiceTeacherPage_ShouldReturnErrorOk_WhenRequestSendFromTeacher() throws Exception {

        mockMvc.perform(post("/teacherSubjects/choiceTeacher")
                .param("teacherId", String.valueOf(1L)))
                .andExpect(status().isOk());
    }

    @WithMockUser(username="admin", password="123",roles="ADMIN")
    @Test
    public void testChoiceTeacherPage_ShouldReturnErrorOk_WhenRequestSendFromAdmin() throws Exception {

        mockMvc.perform(post("/teacherSubjects/choiceTeacher")
                .param("teacherId", String.valueOf(1L)))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="student", password="123",roles="STUDENT")
    @Test
    public void testChoiceTeacherPage_ShouldReturnErrorOk_WhenRequestSendFromStudent() throws Exception {

        mockMvc.perform(post("/teacherSubjects/choiceTeacher")
                .param("teacherId", String.valueOf(1L)))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(username="stuff", password="123",roles="STUFF")
    @Test
    public void testChoiceTeacherPage_ShouldReturnErrorOk_WhenRequestSendFromStuff() throws Exception {

        mockMvc.perform(post("/teacherSubjects/choiceTeacher")
                .param("teacherId", String.valueOf(1L)))
                .andExpect(status().is4xxClientError());
    }

}
