package org.company.ticketonline2.controller;

import lombok.extern.slf4j.Slf4j;
import org.company.ticketonline2.dto.EventDTO;
import org.company.ticketonline2.dto.PlaceDTO;
import org.company.ticketonline2.dto.TicketPackDTO;
import org.company.ticketonline2.model.Event;
import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.service.eventdtoservice.EventDTOService;
import org.company.ticketonline2.service.eventservice.EventService;
import org.company.ticketonline2.service.mapper.MyMapperPlace;
import org.company.ticketonline2.service.mapper.PlaceMapper;
import org.company.ticketonline2.service.placeservice.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDTOService eventDTOService;
    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/events", method= RequestMethod.GET)
    public String listEvent(Model model, Principal principal){
        model.addAttribute("events", eventService.toList());
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

        return "redirect:/events";
    }

    @RequestMapping(value = "/events/create")
    public String createGroup(@ModelAttribute EventDTO eventDTO) {

        log.info("eventDTO={}",
                eventDTO.toString());

        eventDTOService.addEventDTO(eventDTO);

        return "redirect:/events";
    }

    @RequestMapping(value = "/events/delete", method = RequestMethod.DELETE)
    public String deleteGroup(@RequestParam("idEvent") Long idEvent,Model model,
                              Principal principal) {
        Event delEvent = eventService.findById(idEvent);
        if (delEvent!=null) {
           eventService.delete(delEvent);
        }

        return "redirect:/events";    }

    @RequestMapping(value = "/createEvent", method= RequestMethod.GET)
    public String createGroupPage(Model model, Principal principal) {

        model.addAttribute("eventDTO", new EventDTO());

        List<Place> places=placeService.toList();
        List<PlaceDTO> placeDTOs=new ArrayList<>();
        for(Place place:places){
           placeDTOs.add(MyMapperPlace.toDTO(placeService.findById(place.getId())));
        }
        model.addAttribute("placeDTOs", placeDTOs);


        return "createEvent";
    }

    @RequestMapping(value = "/updateEvent",method= RequestMethod.GET)
    public String updateGroupPage(Model model, Principal principal) {

        model.addAttribute("events", eventService.toList());


        return "updateEvent";
    }

    @RequestMapping(value = "/deleteEvent", method= RequestMethod.GET)
    public String deleteGroupPage(Model model, Principal principal) {

        model.addAttribute("events", eventService.toList());

        return "deleteEvent";
    }

}
