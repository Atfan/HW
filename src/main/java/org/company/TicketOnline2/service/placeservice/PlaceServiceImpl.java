package org.company.TicketOnline2.service.placeservice;

import org.company.ticketonline2.dao.place.PlaceRepository;
import org.company.ticketonline2.dto.PlaceDTO;
import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.service.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    PlaceMapper placeMapper;

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public void save(PlaceDTO data) {
        placeRepository.save(placeMapper.toEntity(data));
    }

    @Override
    public int[] saveToList(List<PlaceDTO> arrData) {
        for(PlaceDTO dto : arrData) {
            placeRepository.save(placeMapper.toEntity(dto));
        }
        return new int[1];
    }

    @Override
    public void update(PlaceDTO data) {
        placeRepository.save(placeMapper.toEntity(data));
    }

    @Override
    public void delete(PlaceDTO data) {
        placeRepository.delete(placeMapper.toEntity(data));
    }

    @Override
    public List<PlaceDTO> toList() {
        List<Place> places= placeRepository.findAll();
        List<PlaceDTO> dtos = new ArrayList<>();
        for(Place place : places) {
            dtos.add(placeMapper.toDto(place));
        }
        return dtos;
    }

    @Override
    public void deleteAll() {
        placeRepository.deleteAll();
    }

    @Override
    public PlaceDTO findById(Long id) {
        Optional<Place> byId = placeRepository.findById(id);
        return byId.map(place -> placeMapper.toDto(place)).orElse(null);
    }
}
