package org.company.ticketonline2.service.mapper;

import org.company.ticketonline2.dto.TicketDTO;
import org.company.ticketonline2.dto.TicketPackDTO;
import org.company.ticketonline2.model.Ticket;

import java.util.List;

public interface TicketMapper extends MyMapper<List<Ticket>, TicketPackDTO> {

    TicketDTO toTicketDTO(Ticket ticket);
    Ticket toTicket(TicketDTO ticketDTO);
}
