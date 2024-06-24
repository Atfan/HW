package org.company.ticketonline2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name ="events")
@EqualsAndHashCode(exclude = "tickets")
@ToString(exclude = "tickets")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="event_date")
    private Date date;

    @Column(name="name_event")
    private String name;

    @OneToOne
    @JoinColumn(name="place_id")
    private Place place;

    @OneToMany
    private List<Ticket> tickets;

}
