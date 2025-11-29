package com.hackaton.task.trip.application.in;

import com.hackaton.task.trip.adapter.in.web.dto.TripRouteSaveResponse;
import com.hackaton.task.trip.application.in.command.TripRouteGetCommand;
import com.hackaton.task.trip.domain.Trip;

import java.util.*;

public interface TripRouteGetUseCase {
	TripRouteSaveResponse getTripRoute(TripRouteGetCommand command);
	List<Trip> getAllTripRoutes();
}
