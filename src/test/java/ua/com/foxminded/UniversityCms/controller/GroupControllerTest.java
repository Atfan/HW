package ua.com.foxminded.UniversityCms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.foxminded.UniversityCms.model.Group;
import ua.com.foxminded.UniversityCms.service.groupservice.GroupService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

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
    public void testGetGroupsPage_ShouldReturnListOfGroups_WhenRequestSendFromAdmin() throws Exception {

        List<Group> groups = new ArrayList<>();
        Group addGroup = new Group();
        addGroup.setId(1L);
        addGroup.setGroupName("zm-47");
        addGroup.setDescription("This is zm-47 group");
        groups.add(addGroup);

        addGroup = new Group();
        addGroup.setId(2L);
        addGroup.setGroupName("gr-95");
        addGroup.setDescription("This is gr-95 group");
        groups.add(addGroup);

        when(groupService.findAll()).thenReturn(groups);

        mockMvc.perform(get("/groups/get").with(user("admin").password("123").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("groups", groupService.findAll()))
                .andExpect(view().name("groups"));
    }

    @Test
    public void testGetGroupsPage_ShouldReturnError4xx_WhenRequestSendFromUser() throws Exception {

        mockMvc.perform(get("/groups/get").with(user("user").password("123").roles("USER")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGroupCreatePage_ShouldReturnError4xx_WhenRequestSendFromUser() throws Exception {

        mockMvc.perform(get("/createGroup").with(user("user").password("123").roles("USER")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGroupCreatePage_ShouldReturnOk_WhenRequestSendFromAdmin() throws Exception {

        mockMvc.perform(get("/createGroup").with(user("admin").password("123").roles("ADMIN")))
                .andExpect(status().isOk());
    }

}
