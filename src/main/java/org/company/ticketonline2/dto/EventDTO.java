package org.company.ticketonline2.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String eventName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private List<TicketPackDTO> tickets;
    private PlaceDTO place;
}
