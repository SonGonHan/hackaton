package com.hackaton.task.trip.application.out;

import com.hackaton.task.trip.domain.Trip;

public interface TripRepository {
    void save(Trip trip);

    void delete(Trip trip);
}
