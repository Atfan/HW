package org.company.ticketonline2.service.mapper;

import org.company.ticketonline2.dto.PlaceDTO;
import org.company.ticketonline2.model.Place;

public class MyMapperPlace {
    public static PlaceDTO toDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setPlaceName(place.getName());
        placeDTO.setAddress(place.getAddress());
        return placeDTO;
    }
    public static Place toPlace(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setId(placeDTO.getId());
        place.setName(placeDTO.getPlaceName());
        place.setAddress(placeDTO.getAddress());
        return place;
    }
}
