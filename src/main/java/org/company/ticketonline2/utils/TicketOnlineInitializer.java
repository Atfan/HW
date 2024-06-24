package org.company.ticketonline2.utils;

import lombok.extern.log4j.Log4j2;
import org.company.ticketonline2.exception.FileException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TicketOnlineInitializer {
    private final TicketDBInitializer ticketDBInitializer;

    public TicketOnlineInitializer(TicketDBInitializer ticketDBInitializer) {
        this.ticketDBInitializer = ticketDBInitializer;
    }

    public void initialize() {
        try {
            log.info("Start Ticket-DB initialize");
            ticketDBInitializer.deleteAllRowsInDB();
            ticketDBInitializer.createRandomCustomer();
            ticketDBInitializer.createRandomPlace();
            ticketDBInitializer.createRandomEvents();
            log.info("End Ticket-DB initialize");
        } catch (FileException e) {
            log.error("error with source file names");
        }
    }
}
