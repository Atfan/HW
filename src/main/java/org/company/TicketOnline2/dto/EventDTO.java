package org.company.TicketOnline2.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String eventName;
    private Date date;
    private List<TicketPackDTO> tickets;
    private PlaceDTO place;
}
