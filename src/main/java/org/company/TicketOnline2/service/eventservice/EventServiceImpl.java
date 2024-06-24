package org.company.TicketOnline2.service.eventservice;

import org.company.ticketonline2.dao.event.EventRepository;
import org.company.ticketonline2.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void save(Event data) {
        eventRepository.save(data);
    }

    @Override
    public int[] saveToList(List<Event> arrData) {
        eventRepository.saveAll(arrData);
        return new int[1];
    }

    @Override
    public void update(Event data) {
        eventRepository.save(data);
    }

    @Override
    public void delete(Event data) {
        eventRepository.delete(data);
    }

    @Override
    public List<Event> toList() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }

    @Override
    public Event findById(Long id) {
        Optional<Event> byId = eventRepository.findById(id);
        return byId.orElse(null);
    }
}
