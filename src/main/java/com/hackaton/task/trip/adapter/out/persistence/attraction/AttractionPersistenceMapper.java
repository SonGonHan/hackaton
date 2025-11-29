package com.hackaton.task.trip.adapter.out.persistence.attraction;

import com.hackaton.task.trip.domain.Attraction;
import org.springframework.stereotype.Component;

@Component
public class AttractionPersistenceMapper {

    public Attraction toDomain(AttractionEntity entity) {
        return new Attraction(entity.getId(), entity.getName(), entity.getLatitude(), entity.getLongitude());
//        return Attraction.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .latitude(entity.getLatitude())
//                .longitude(entity.getLongitude())
//                .build();
    }

    public AttractionEntity toEntity(Attraction domain) {
        return AttractionEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .latitude(domain.getLatitude())
                .longitude(domain.getLongitude())
                .build();
    }
}
