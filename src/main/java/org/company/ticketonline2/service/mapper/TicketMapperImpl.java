package org.company.ticketonline2.service.mapper;

import org.company.ticketonline2.dto.TicketDTO;
import org.company.ticketonline2.dto.TicketPackDTO;
import org.company.ticketonline2.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketMapperImpl implements TicketMapper {
    EventMapper eventMapper;
    @Override
    public List<Ticket> toEntity(TicketPackDTO dto) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < dto.getCount(); i++) {
            Ticket ticket = new Ticket();
            ticket.setCost(dto.getCost());
            ticket.setStatus("FREE");
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    public TicketPackDTO toDto(List<Ticket> entity) {
        if (entity.isEmpty()) {
            return new TicketPackDTO(0, 0);
        }
        double cost = entity.get(0).getCost();
        int count = (int) entity.stream().filter(ticket -> ticket.getCost() == cost).count();
        return new TicketPackDTO(cost, count);
    }

    @Override
    public TicketDTO toTicketDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setCost(ticket.getCost());
        dto.setNumbr(ticket.getNumbr());
        dto.setCustomer(ticket.getCustomer() != null ? ticket.getCustomer() : null);
        dto.setEvent(eventMapper.toDto(ticket.getEvent() != null ? ticket.getEvent() : null));
        dto.setStatus(ticket.getStatus());
        return dto;
    }

    @Override
    public Ticket toTicket(TicketDTO ticketDTO) {
        if (ticketDTO == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setCost(ticketDTO.getCost());
        ticket.setNumbr(ticketDTO.getNumbr());
        ticket.setStatus(ticketDTO.getStatus());
        return ticket;
    }
}
