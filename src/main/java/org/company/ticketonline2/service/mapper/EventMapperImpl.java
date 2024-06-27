package org.company.ticketonline2.service.mapper;

import org.company.ticketonline2.dto.EventDTO;
import org.company.ticketonline2.dto.TicketPackDTO;
import org.company.ticketonline2.model.Event;
import org.company.ticketonline2.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapperImpl {


    public static Event toEntity(EventDTO dto) {
        Event event = new Event();
        event.setId(dto.getId());
        event.setName(dto.getEventName());
        event.setPlace(MyMapperPlace.toPlace(dto.getPlace()));
        List<Ticket> tickets = new ArrayList<>();
        long idTck=0;
        for(int i=0;i<dto.getTickets().size();i++) {
            for(int j=0; j<dto.getTickets().get(i).getCount();j++) {
                Ticket ticket = new Ticket();
                ticket.setId(idTck);
                ticket.setCost(dto.getTickets().get(i).getCost());
                ticket.setEvent(event);
                ticket.setNumbr((int)idTck);
                ticket.setStatus("FREE");
                tickets.add(ticket);
            }
        }
        event.setDate(dto.getDate());
        return event;
    }

    public static EventDTO toDto(Event entity) {
        EventDTO dto = new EventDTO();
        dto.setId(entity.getId());
        dto.setEventName(entity.getName());
        dto.setDate(entity.getDate());
        dto.setPlace(MyMapperPlace.toDTO(entity.getPlace()));

        List<TicketPackDTO> ticketPacks = entity.getTickets().stream()
                .collect(Collectors.groupingBy(Ticket::getCost))
                .entrySet().stream()
                .map(entry -> {
                    TicketPackDTO ticketPackDTO = new TicketPackDTO();
                    ticketPackDTO.setCost(entry.getKey());
                    ticketPackDTO.setCount(entry.getValue().size());
                    return ticketPackDTO;
                })
                .collect(Collectors.toList());

        dto.setTickets(ticketPacks);

        return dto;
    }
}
