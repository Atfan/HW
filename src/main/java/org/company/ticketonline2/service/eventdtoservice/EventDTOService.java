package org.company.ticketonline2.service.eventdtoservice;

import org.company.ticketonline2.dto.EventDTO;
import org.company.ticketonline2.dto.TicketDTO;
import org.company.ticketonline2.dto.TicketPackDTO;

import java.util.List;

public interface EventDTOService{
        void addEventDTO(EventDTO eventDTO);
        void updateEventDTO(EventDTO eventDTO);
        void deleteEventDTO(EventDTO eventDTO);
        EventDTO getEventDTOById(long id);
        List<EventDTO> getAllEventDTO();
        List<TicketPackDTO> getAllTicketPackDTO();
        List<TicketDTO> getTicketPackDTOByIdEvent(long id);
}
