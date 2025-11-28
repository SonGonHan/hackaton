package com.hackaton.task.trip.application.in;

import com.hackaton.task.trip.adapter.in.web.dto.TripRoadResponse;
import com.hackaton.task.trip.domain.Attraction;

import java.util.List;

public interface MakeRoadUseCase {
    TripRoadResponse makeRoad(Long distance, List<Attraction> attractions);
}
