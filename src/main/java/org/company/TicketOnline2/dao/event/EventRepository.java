package org.company.TicketOnline2.dao.event;

import org.company.ticketonline2.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository  extends JpaRepository<Event,Long> {
}
