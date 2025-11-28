package com.hackaton.task.trip.adapter.out.persistence.trip;

import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionEntity;
import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionPersistenceMapper;
import com.hackaton.task.trip.domain.Attraction;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TripPersistenceMapper {

    private final AttractionPersistenceMapper mapper;

    public Trip toDomain(TripEntity entity) {
        Set<Attraction> attractionSet = entity.getAttractions().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toSet());

        return Trip.builder()
                .id(entity.getId())
                .name(entity.getName())
                .attractions(attractionSet)
                .build();
    }

    public TripEntity toEntity(Trip domain) {
        Set<AttractionEntity> attractionEntitySet = domain.getAttractions().stream()
                .map(mapper::toEntity)
                .collect(Collectors.toSet());

        return TripEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .attractions(attractionEntitySet)
                .build();
    }
}
