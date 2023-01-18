package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.UniversityCms.model.Group;
import ua.com.foxminded.UniversityCms.service.groupservice.GroupService;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/groups/get", method = RequestMethod.GET)
    public String getGroups(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "groups";
    }

    @RequestMapping(value = "/groups/update", method = RequestMethod.POST)
    public String updateGroup(@RequestParam("groupName") String groupName,
                              @RequestParam("updateGroupName") String updateGroupName,
                              @RequestParam("updateGroupDescription") String updateGroupDescription,
                              Model model) {
        List<Group> findGroups = groupService.findByGroupNameLike(groupName);
        if (findGroups.size() != 0) {
            Group updateGroup = findGroups.get(0);
            updateGroup.setGroupName(updateGroupName);
            updateGroup.setDescription(updateGroupDescription);
            groupService.save(updateGroup);
        }
        model.addAttribute("groups", groupService.findAll());
        return "updateGroup";
    }

    @RequestMapping(value = "/groups/create", method = RequestMethod.POST)
    public String createGroup(@RequestParam("groupName") String groupName,
                              @RequestParam("groupDescription") String groupDescription,Model model) {
        Group addGroup = new Group();
        addGroup.setGroupName(groupName);
        addGroup.setDescription(groupDescription);
        groupService.save(addGroup);
        return "createGroup";
    }

    @RequestMapping(value = "/groups/delete", method = RequestMethod.POST)
    public String deleteGroup(@RequestParam("groupName") String groupName,Model model) {
        List<Group> findGroups = groupService.findByGroupNameLike(groupName);
        if (findGroups.size() != 0) {
            Group deleteGroup = findGroups.get(0);
            groupService.delete(deleteGroup);
        }
        model.addAttribute("groups", groupService.findAll());
        return "deleteGroup";    }

    @GetMapping(value = "/createGroup")
    public String createGroupPage(Model model) {
        return "createGroup";
    }

    @GetMapping(value = "/updateGroup")
    public String updateGroupPage(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "updateGroup";
    }

    @GetMapping(value = "/deleteGroup")
    public String deleteGroupPage(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "deleteGroup";
    }
}
