package org.company.ticketonline2.service.mapper;

import org.company.ticketonline2.dto.PlaceDTO;
import org.company.ticketonline2.model.Place;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(config = MapperConfig.class)
public interface PlaceMapper {

    Place toEntity(PlaceDTO placeDTO);

    PlaceDTO toDto(Place place);

}
