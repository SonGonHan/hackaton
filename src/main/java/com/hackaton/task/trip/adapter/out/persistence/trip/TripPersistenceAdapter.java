package com.hackaton.task.trip.adapter.out.persistence.trip;

import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionPersistenceMapper;
import com.hackaton.task.trip.application.out.TripRepository;
import com.hackaton.task.trip.domain.Attraction;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TripPersistenceAdapter implements TripRepository {

    private final TripJpaRepository repository;
    private final TripPersistenceMapper tripMapper;
    private final AttractionPersistenceMapper attractionMapper;

    @Override
    public void save(Trip trip) {
        var tripEntity = tripMapper.toEntity(trip);
        repository.save(tripEntity);
    }

    @Override
    public void delete(Trip trip) {
        var tripEntity = tripMapper.toEntity(trip);
        repository.delete(tripEntity);
    }

    @Override
    public Optional<Trip> findById(Long id) {
        return repository.findById(id).map(tripMapper::toDomain);
    }

    @Override
    public List<Attraction> findAttractionsByTripId(Long tripId) {
        return repository.findAttractionsByTripId(tripId).stream()
                .map(attractionMapper::toDomain)
                .toList();
    }

}
