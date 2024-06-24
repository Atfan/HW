package org.company.TicketOnline2.service.ticketservice;

import org.company.ticketonline2.dao.ticket.TicketRepository;
import org.company.ticketonline2.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void save(Ticket data) {
        ticketRepository.save(data);
    }

    @Override
    public int[] saveToList(List<Ticket> arrData) {
        ticketRepository.saveAll(arrData);
        return new int[1];
    }

    @Override
    public void update(Ticket data) {
        ticketRepository.save(data);
    }

    @Override
    public void delete(Ticket data) {
        ticketRepository.delete(data);
    }

    @Override
    public List<Ticket> toList() {
        return ticketRepository.findAll();
    }

    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }

    @Override
    public Ticket findById(Long id) {
        Optional<Ticket> byId = ticketRepository.findById(id);
        return byId.orElse(null);
    }
}
