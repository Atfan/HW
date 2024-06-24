package org.company.ticketonline2.service.placeservice;

import org.company.ticketonline2.dao.place.PlaceRepository;
import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.service.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public void save(Place data) {
        placeRepository.save(data);
    }

    @Override
    public int[] saveToList(List<Place> arrData) {
        placeRepository.saveAll(arrData);
        return new int[1];
    }

    @Override
    public void update(Place data) {
        placeRepository.save(data);
    }

    @Override
    public void delete(Place data) {
        placeRepository.delete(data);
    }

    @Override
    public List<Place> toList() {
        return placeRepository.findAll();
    }

    @Override
    public void deleteAll() {
        placeRepository.deleteAll();
    }

    @Override
    public Place findById(Long id) {
        Optional<Place> byId = placeRepository.findById(id);
        return byId.orElse(null);
    }
}
