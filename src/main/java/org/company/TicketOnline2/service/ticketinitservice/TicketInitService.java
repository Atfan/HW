package org.company.TicketOnline2.service.ticketinitservice;

import org.company.ticketonline2.model.Customer;
import org.company.ticketonline2.model.Event;
import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketInitService {

    List<Customer> findAllCustomers();

    List<Place> findAllPlaces();

    List<Event> findAllEvents();

    List<Ticket> findAllTickets();

    void deleteAllRowsInDB();

    void saveTicket(List<Ticket> tickets);

    void saveCustomer(List<Customer> customer);

    void savePlace(List<Place> place);

    void saveEvent(List<Event> event);

}
