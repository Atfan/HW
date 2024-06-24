package org.company.TicketOnline2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketPackDTO {
    private double cost;
    private int count;
}
