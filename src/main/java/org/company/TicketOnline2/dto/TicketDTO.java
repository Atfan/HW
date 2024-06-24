package org.company.TicketOnline2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.company.ticketonline2.model.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private long id;
    private double cost;
    private int numbr;
    private String status;
    private EventDTO event;
    private Customer customer;
}
