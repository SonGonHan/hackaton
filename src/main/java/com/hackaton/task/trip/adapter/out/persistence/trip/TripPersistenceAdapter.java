package com.hackaton.task.trip.adapter.out.persistence.trip;

import com.hackaton.task.trip.application.out.TripRepository;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TripPersistenceAdapter implements TripRepository {

    private final TripJpaRepository repository;
    private final TripPersistenceMapper mapper;


    @Override
    public void save(Trip trip) {
        var tripEntity = mapper.toEntity(trip);
        repository.save(tripEntity);
    }

    @Override
    public void delete(Trip trip) {
        var tripEntity = mapper.toEntity(trip);
        repository.delete(tripEntity);
    }
}
