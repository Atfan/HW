package org.company.ticketonline2.controller;

import org.company.ticketonline2.dto.EventDTO;
import org.company.ticketonline2.dto.PlaceDTO;
import org.company.ticketonline2.dto.TicketPackDTO;
import org.company.ticketonline2.model.Event;
import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.service.eventdtoservice.EventDTOService;
import org.company.ticketonline2.service.eventservice.EventService;
import org.company.ticketonline2.service.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDTOService eventDTOService;

    @RequestMapping(value = "/events", method= RequestMethod.GET)
    public String listEvent(Model model, Principal principal){
        model.addAttribute("events", eventService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "events";
    }

    @RequestMapping(value = "/events/update", method = RequestMethod.POST)
    public String updateGroup(@RequestParam("idEvent") Long idEvent,
                              @RequestParam("nameEvent") String nameEvent,
                              @RequestParam("placeEvent") Place placeEvent,
                              @RequestParam("dateEvent") Date dateEvent,
                              Model model, Principal principal) {
        Event findEvent = eventService.findById(idEvent);

        findEvent.setName(nameEvent);
        findEvent.setPlace( placeEvent);
        findEvent.setDate(dateEvent);
        eventService.update(findEvent);

        model.addAttribute("events", eventService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "updateEvent";
    }

    @RequestMapping(value = "/events/create", method = RequestMethod.POST)
    public String createGroup(
                                @RequestParam("nameEvent") String nameEvent,
                                @RequestParam("placeEvent") PlaceDTO placeEvent,
                                @RequestParam("dateEvent") Date dateEvent,
                                @RequestParam("tickets") List<TicketPackDTO> tickets,
                                Model model, Principal principal) {
        EventDTO addEvent = new EventDTO();
        addEvent.setEventName(nameEvent);
        addEvent.setPlace(placeEvent);
        addEvent.setDate(dateEvent);
        addEvent.setTickets(tickets);
        eventDTOService.addEventDTO(addEvent);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "createEvent";
    }

    @RequestMapping(value = "/events/delete", method = RequestMethod.POST)
    public String deleteGroup(@RequestParam("idEvent") Long idEvent,Model model,
                              Principal principal) {
        Event delEvent = eventService.findById(idEvent);
        if (delEvent!=null) {
           eventService.delete(delEvent);
        }

        model.addAttribute("events", eventService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "deleteEvents";    }

    @GetMapping(value = "/createEvent")
    public String createGroupPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "createEvent";
    }

    @GetMapping(value = "/updateEvent")
    public String updateGroupPage(Model model, Principal principal) {
        model.addAttribute("events", eventService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "updateEvent";
    }

    @GetMapping(value = "/deleteEvent")
    public String deleteGroupPage(Model model, Principal principal) {
        model.addAttribute("events", eventService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "deleteEvent";
    }

}
