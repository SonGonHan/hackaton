package com.hackaton.task.trip.application.out;

import com.hackaton.task.trip.domain.Attraction;

public interface AttractionRepository {
    void save(Attraction attraction);

    void delete(Attraction attraction);


}
