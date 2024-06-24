package org.company.ticketonline2.service;

import java.util.ArrayList;
import java.util.List;

public enum Endpoints {
    HOME("/"),
    LOGIN("/login"),
    LOGOUT("/logout"),
    ADMIN_VIEW("/admin"),
    EVENTS_VIEW("/events"),
    EVENTS_CREATE("/events/create"),
    EVENTS_UPDATE("/events/update"),
    EVENTS_DELETE("/events/delete"),
    EVENT_UPDATE_VIEW("/updateEvent"),
    EVENT_DELETE_VIEW("/deleteEvent"),
    //TICKETS_VIEW("events/{id}/tickets"),
    PLACES_VIEW("/places"),
    PLACES_CREATE("/places/create"),
    PLACES_UPDATE("/places/update"),
    PLACES_DELETE("/places/delete"),
    PlACE_UPDATE_VIEW("/updatePlace"),
    PLACE_DELETE_VIEW("/deletePlace"),
    CUSTOMERS_VIEW("/customers"),
    CUSTOMERS_CREATE("/customers/create"),
    CUSTOMERS_UPDATE("/customers/update"),
    CUSTOMERS_DELETE("/customers/delete"),
    CUSTOMER_UPDATE_VIEW("/updateCustomer"),
    CUSTOMER_DELETE_VIEW("/deleteCustomer")
    ;

    private String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    static public List<Endpoints> getEndpointForAllUsers() {
        List<Endpoints> endpoints = new ArrayList<>();
        endpoints.add(HOME);
        endpoints.add(LOGIN);
        endpoints.add(LOGOUT);
        return endpoints;
    }

    static public List<Endpoints> getEndpointForAdminStuff() {
        List<Endpoints> endpoints = new ArrayList<>();
        endpoints.add(ADMIN_VIEW);
        endpoints.add(EVENTS_VIEW);
        endpoints.add(EVENTS_CREATE);
        endpoints.add(EVENTS_UPDATE);
        endpoints.add(EVENTS_DELETE);
        endpoints.add(PLACES_VIEW);
        endpoints.add(PLACES_CREATE);
        endpoints.add(PLACES_UPDATE);
        endpoints.add(PLACES_DELETE);
        endpoints.add(CUSTOMERS_VIEW);
        endpoints.add(CUSTOMERS_CREATE);
        endpoints.add(CUSTOMERS_UPDATE);
        endpoints.add(CUSTOMERS_DELETE);
        endpoints.add(CUSTOMER_UPDATE_VIEW);
        endpoints.add(CUSTOMER_DELETE_VIEW);
        endpoints.add(CUSTOMER_UPDATE_VIEW);
        endpoints.add(CUSTOMER_DELETE_VIEW);
        endpoints.add(CUSTOMER_UPDATE_VIEW);
        endpoints.add(CUSTOMER_DELETE_VIEW);

        return endpoints;
    }

}
