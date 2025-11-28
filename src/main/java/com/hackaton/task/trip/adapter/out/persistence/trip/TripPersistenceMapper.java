package com.hackaton.task.trip.adapter.out.persistence.trip;

import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionPersistenceMapper;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripPersistenceMapper {

    private final AttractionPersistenceMapper mapper;

    public Trip toDomain(TripEntity entity) {
        var attractionList = entity.getAttractions().stream()
                .map(mapper::toDomain)
                .toList();

        return Trip.builder()
                .id(entity.getId())
                .name(entity.getName())
                .attractions(attractionList)
                .build();
    }

    public TripEntity toEntity(Trip domain) {
        var attractionList = domain.getAttractions().stream()
                .map(mapper::toEntity)
                .toList();

        return TripEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .attractions(attractionList)
                .build();
    }
}
