package ua.com.foxminded.UniversityCms.service.groupservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.UniversityCms.AppStarter;
import ua.com.foxminded.UniversityCms.UniversityCmsApplication;
import ua.com.foxminded.UniversityCms.dao.group.GroupRepository;
import ua.com.foxminded.UniversityCms.model.Group;
import ua.com.foxminded.UniversityCms.utils.TestConteinersConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@ContextConfiguration(classes = UniversityCmsApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class GroupServiceImplTest {

    @MockBean
    private AppStarter appStarter;

    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void save_ShuldAddNewRowInTheTableGroups_WhenCalled() {
        List<Group> expectedResult = new ArrayList<>();
        Group addGroup = new Group();
        addGroup.setId(1L);
        addGroup.setGroupName("rz-20");
        addGroup.setDescription("This is rz-20 group");
        expectedResult.add(addGroup);

        groupRepository.save(addGroup);
        List<Group> actualResult = groupRepository.findAll();

        assertEquals(expectedResult,
                actualResult,
                "Execute method save from groups");
    }


}
