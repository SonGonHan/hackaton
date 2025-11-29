package com.hackaton.task.trip.application.in;

import com.hackaton.task.trip.adapter.in.web.dto.TripRouteSaveResponse;
import com.hackaton.task.trip.application.in.command.TripRouteGetCommand;

public interface TripRouteGetUseCase {
	TripRouteSaveResponse getTripRoute(TripRouteGetCommand command);
}
