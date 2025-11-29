package com.hackaton.task.trip.application.in;

import com.hackaton.task.trip.application.in.command.TripRouteSaveCommand;

public interface TripRouteSaveUseCase {
	void saveRoute(TripRouteSaveCommand command);
}
