package com.hackaton.task.trip.application.usecase;

import com.hackaton.task.trip.adapter.in.web.dto.TripRoadResponse;
import com.hackaton.task.trip.application.in.MakeRoadUseCase;
import com.hackaton.task.trip.domain.Attraction;

import java.util.ArrayList;
import java.util.List;

public class MakeRoadService implements MakeRoadUseCase {

    @Override
    public TripRoadResponse makeRoad(Long distance, List<Attraction> attractions) {
        List<Attraction> ans = new ArrayList<>(attractions);
        return new TripRoadResponse(ans);
    }
}
