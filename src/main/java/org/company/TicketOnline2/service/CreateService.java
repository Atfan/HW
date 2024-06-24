package org.company.TicketOnline2.service;

import org.company.ticketonline2.service.mapper.EventMapper;
import org.company.ticketonline2.service.ticketservice.TicketService;
import org.springframework.stereotype.Service;

@Service
public class CreateService {
    private EventMapper eventMapper;
    private TicketService ticketService;
}
