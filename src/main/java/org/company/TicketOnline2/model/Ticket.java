package org.company.TicketOnline2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@Table(name ="tickets")
@EqualsAndHashCode(exclude = "event")
@ToString(exclude = "event")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cost;
    private int numbr;
    private String status;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="event_id", nullable=false)
    private Event event;
}
