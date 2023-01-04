package ua.com.foxminded.UniversityCms.service.groupservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.UniversityCms.dao.group.GroupRepository;
import ua.com.foxminded.UniversityCms.model.Group;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public int[] saveStudentsList(List<Group> groups) {
        groupRepository.saveAll(groups);
        return new int[0];
    }

    @Override
    public void update(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void deleteAll() {
        groupRepository.deleteAll();
    }

}
