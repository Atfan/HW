package org.company.TicketOnline2.service.ticketinitservice;

import org.company.ticketonline2.dao.customer.CustomerRepository;
import org.company.ticketonline2.dao.event.EventRepository;
import org.company.ticketonline2.dao.place.PlaceRepository;
import org.company.ticketonline2.dao.ticket.TicketRepository;
import org.company.ticketonline2.model.Customer;
import org.company.ticketonline2.model.Event;
import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketInitServiceImpl implements TicketInitService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Place> findAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void deleteAllRowsInDB() {
        placeRepository.deleteAll();
        eventRepository.deleteAll();
        ticketRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Override
    public void saveTicket(List<Ticket> tickets) {
        ticketRepository.saveAll(tickets);
    }

    @Override
    public void saveCustomer(List<Customer> customer) {
        customerRepository.saveAll(customer);
    }

    @Override
    public void savePlace(List<Place> place) {
        placeRepository.saveAll(place);
    }

    @Override
    public void saveEvent(List<Event> event) {
        eventRepository.saveAll(event);
    }
}
