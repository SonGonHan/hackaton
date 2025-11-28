package com.hackaton.task.trip.adapter.out.persistence.trip;

import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionEntity;
import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionPersistenceMapper;
import com.hackaton.task.trip.domain.Attraction;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TripPersistenceMapper {

    private final AttractionPersistenceMapper mapper;

    public Trip toDomain(TripEntity entity) {
        var attractionSet = entity.getAttractions().stream()
                .map(mapper::toDomain)
                .toList();

        return Trip.builder()
                .id(entity.getId())
                .name(entity.getName())
                .attractions(attractionSet)
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
