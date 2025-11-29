package com.hackaton.task.trip.application.usecase;

import com.hackaton.task.trip.adapter.in.web.dto.TripRouteSaveResponse;
import com.hackaton.task.trip.application.in.TripRouteGetUseCase;
import com.hackaton.task.trip.application.in.command.TripRouteGetCommand;
import com.hackaton.task.trip.application.out.TripRepository;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripRouteGetService implements TripRouteGetUseCase {

	private final TripRepository tripRepository;

	@Override
	public TripRouteSaveResponse getTripRoute(TripRouteGetCommand command) {
		Trip trip = tripRepository.findById(command.id())
				.orElseThrow(() -> new IllegalArgumentException("Trip not found with id: " + command.id()));

		return new TripRouteSaveResponse(trip.getName(), trip.getAttractions());
	}
}
