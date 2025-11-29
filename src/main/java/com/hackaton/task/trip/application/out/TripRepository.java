package com.hackaton.task.trip.application.out;

import com.hackaton.task.trip.domain.Attraction;
import com.hackaton.task.trip.domain.Trip;

import java.util.List;
import java.util.Optional;

public interface TripRepository {
    void save(Trip trip);

    void saveRef(Trip trip, List<Attraction> attractions);

    void delete(Trip trip);

    Optional<Trip> findById(Long id);

    List<Attraction> findAttractionsById(Long tripId);
}
