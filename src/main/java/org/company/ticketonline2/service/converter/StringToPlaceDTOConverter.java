package org.company.ticketonline2.service.converter;

import org.company.ticketonline2.dto.PlaceDTO;
import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.service.mapper.MyMapperPlace;
import org.company.ticketonline2.service.placeservice.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPlaceDTOConverter implements Converter<String, PlaceDTO> {

    @Autowired
    private PlaceService placeService;

    @Override
    public PlaceDTO convert(String source) {
        return MyMapperPlace.toDTO(placeService.findById(Long.parseLong(source)));
    }


}
