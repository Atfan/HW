package org.company.ticketonline2.controller;

import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.service.placeservice.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

@Controller
public class PlaceController {

    @Autowired
    private PlaceService placeService;


    @RequestMapping(value = "/places", method= RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String places(Model model, Principal principal){
        model.addAttribute("places", placeService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "places";
    }

    @RequestMapping(value = "/places/update", method = RequestMethod.POST)
    public String updatePlace(@RequestParam("idPlace") Long idPlace,
                              @RequestParam("namePlace") String namePlace,
                              @RequestParam("address") String address,
                              Model model, Principal principal) {
        Place findPlace = placeService.findById(idPlace);

        findPlace.setName(namePlace);
        findPlace.setAddress(address);
        placeService.update(findPlace);

        model.addAttribute("places", placeService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "updatePlace";
    }

    @RequestMapping(value = "/places/create", method = RequestMethod.POST)
    public String createPlace(
            @RequestParam("namePlace") String namePlace,
            @RequestParam("address") String address,
            Model model, Principal principal) {
        Place addPlace = new Place();
        addPlace.setName(namePlace);
        addPlace.setAddress(address);

        placeService.save(addPlace);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "createPlace";
    }

    @RequestMapping(value = "/places/delete", method = RequestMethod.POST)
    public String deletePlace(@RequestParam("idPlace") Long idPlace,Model model,
                              Principal principal) {
        Place delPlace = placeService.findById(idPlace);
        if (delPlace!=null) {
            placeService.delete(delPlace);
        }

        model.addAttribute("places", placeService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "deletePlace";    }

    @RequestMapping(value = "/createPlace", method= RequestMethod.GET)
    public String createPlacePage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "createPlace";
    }

    @RequestMapping(value = "/updatePlace", method= RequestMethod.GET)
    public String updatePlacePage(Model model, Principal principal) {
        model.addAttribute("places", placeService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "updatePlace";
    }

    @RequestMapping(value = "/deletePlace", method= RequestMethod.GET)
    public String deletePlacePage(Model model, Principal principal) {
        model.addAttribute("places", placeService.toList());

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
        StringBuilder roles = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            roles.append(authority.getAuthority());
        }

        model.addAttribute("roles", roles.toString());

        return "deletePlace";
    }
}
