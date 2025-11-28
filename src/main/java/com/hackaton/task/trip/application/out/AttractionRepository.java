package com.hackaton.task.trip.application.out;

import com.hackaton.task.trip.domain.Attraction;
import com.hackaton.task.trip.domain.Trip;

import java.util.List;
import java.util.Optional;

public interface AttractionRepository {
    void save(Attraction attraction);

    void delete(Attraction attraction);

    Optional<Attraction> findById(Long id);

}
