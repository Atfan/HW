package org.company.ticketonline2.service.eventdtoservice;

import org.company.ticketonline2.dto.EventDTO;
import org.company.ticketonline2.dto.TicketDTO;
import org.company.ticketonline2.dto.TicketPackDTO;
import org.company.ticketonline2.model.Event;
import org.company.ticketonline2.model.Ticket;
import org.company.ticketonline2.service.eventservice.EventService;
import org.company.ticketonline2.service.mapper.EventMapperImpl;
import org.company.ticketonline2.service.mapper.TicketMapperImpl;
import org.company.ticketonline2.service.ticketservice.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventDTOServiceImpl implements EventDTOService {
    @Autowired
    EventService eventService;
    @Autowired
    TicketService ticketService;

    @Override
    public void addEventDTO(EventDTO eventDTO) {
        Event event = EventMapperImpl.toEntity(eventDTO);
        eventService.save(event);
        // Create tickets based on the TicketPackDTO
        List<Ticket> tickets = new ArrayList<>();
        for (TicketPackDTO pack : eventDTO.getTickets()) {
            tickets.addAll(TicketMapperImpl.toEntity(pack));
        }
        tickets.forEach(ticket -> ticket.setEvent(event));
        ticketService.saveToList(tickets);
    }

    @Override
    public void updateEventDTO(EventDTO eventDTO) {
        Event event = EventMapperImpl.toEntity(eventDTO);
        eventService.update(event);
    }

    @Override
    public void deleteEventDTO(EventDTO eventDTO) {
        Event event = EventMapperImpl.toEntity(eventDTO);
        eventService.delete(event);
    }

    @Override
    public EventDTO getEventDTOById(long id) {
        Event event = eventService.findById(id);
        return EventMapperImpl.toDto(event);
    }

    @Override
    public List<EventDTO> getAllEventDTO() {
        List<Event> events = eventService.toList();
        return events.stream()
                .map(EventMapperImpl::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketPackDTO> getAllTicketPackDTO() {
        List<Ticket> tickets = ticketService.toList();
        Map<Event, List<Ticket>> groupedTickets = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getEvent));
        List<TicketPackDTO> ticketPackDTOs = new ArrayList<>();
        for (Map.Entry<Event, List<Ticket>> entry : groupedTickets.entrySet()) {
            ticketPackDTOs.addAll(entry.getValue().stream()
                    .collect(Collectors.groupingBy(Ticket::getCost))
                    .entrySet().stream()
                    .map(e -> new TicketPackDTO(e.getKey(), e.getValue().size()))
                    .toList());
        }
        return ticketPackDTOs;
    }

    @Override
    public List<TicketDTO> getTicketPackDTOByIdEvent(long id) {
        List<Ticket> tickets = ticketService.findById(id).getEvent().getTickets();

        return tickets.stream()
                .map(TicketMapperImpl::toTicketDTO)  // Преобразование каждого Ticket в TicketDTO
                .collect(Collectors.toList());
    }
}
