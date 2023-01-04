package ua.com.foxminded.UniversityCms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.foxminded.UniversityCms.service.groupservice.GroupService;

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
    public String updateGroup(Model model) {
        return null;
    }

    @RequestMapping(value = "/groups/create", method = RequestMethod.POST)
    public String createGroup(Model model) {
        return null;
    }

    @RequestMapping(value = "/groups/delete", method = RequestMethod.POST)
    public String deleteGroup(Model model) {
        return null;
    }

}
