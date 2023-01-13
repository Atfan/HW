package ua.com.foxminded.UniversityCms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.foxminded.UniversityCms.AppStarter;
import ua.com.foxminded.UniversityCms.UniversityCmsApplication;
import ua.com.foxminded.UniversityCms.model.Subject;
import ua.com.foxminded.UniversityCms.service.subjectservice.SubjectService;
import ua.com.foxminded.UniversityCms.utils.TestConteinersConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@ContextConfiguration(classes = UniversityCmsApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
@AutoConfigureMockMvc
public class SubjectControllerIntegrationTest {

    @MockBean
    private AppStarter appStarter;

    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SubjectService subjectService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testCreateSubjectsPage_ShouldSaveNewSubjectInDb_WhenRequestSendFromAdmin() throws Exception {

        assertThat(subjectService.findAll()).isEmpty();

        mockMvc.perform(post("/subjects/create")
                                .with(user("admin").password("123").roles("ADMIN"))
                                .param("subjectName","mathematics"))
                .andExpect(status().isOk())
                .andExpect(view().name("createCourse"));

        Subject addSubject = new Subject();
        addSubject.setId(1L);
        addSubject.setSubjectName("mathematics");

        List<Subject> expectedResult = new ArrayList<>();
        expectedResult.add(addSubject);

        List<Subject> actualResult = subjectService.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method save from subjects");
    }


}
