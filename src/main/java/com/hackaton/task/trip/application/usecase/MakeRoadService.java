package com.hackaton.task.trip.application.usecase;

import com.hackaton.task.trip.adapter.in.web.dto.TripRoadResponse;
import com.hackaton.task.trip.application.in.MakeRoadUseCase;
import com.hackaton.task.trip.application.in.command.MakeRoadCommand;
import com.hackaton.task.trip.domain.Attraction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MakeRoadService implements MakeRoadUseCase {

    @Override
    public TripRoadResponse makeRoad(MakeRoadCommand command) {
        List<Attraction> ans = new ArrayList<>(command.attractions());
        return new TripRoadResponse(ans);
    }
}
