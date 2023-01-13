package ua.com.foxminded.UniversityCms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UniversityControllerTest {

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

    @Test
    public void testHome() throws Exception {

        mockMvc.perform(get("/","/welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"));

    }

    @Test
    public void testAdmin() throws Exception {

        mockMvc.perform(get("/admin").with(user("admin").password("123").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPage"))
                .andExpect(model().attribute("userInfo", "UserName:admin (ROLE_ADMIN)"));

    }

    @Test
    public void testAdminPage_ShouldReturnError4xx_WhenStudentCome() throws Exception {

        mockMvc.perform(get("/admin").with(user("student").password("123").roles("STUDENT")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testAdminPage_ShouldReturnOk_WhenStuffCome() throws Exception {

        mockMvc.perform(get("/admin").with(user("stuff").password("123").roles("STUFF")))
                .andExpect(status().isOk());
    }

}
