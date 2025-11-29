package com.hackaton.task.trip.application.usecase;

import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionJpaRepository;
import com.hackaton.task.trip.application.in.TripRouteSaveUseCase;
import com.hackaton.task.trip.application.in.command.TripRouteSaveCommand;
import com.hackaton.task.trip.application.out.AttractionRepository;
import com.hackaton.task.trip.application.out.TripRepository;
import com.hackaton.task.trip.domain.Attraction;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TripRouteSaveService implements TripRouteSaveUseCase {

	private final TripRepository tripRepository;
	private final AttractionJpaRepository attractionRepository;

	@Override
	public void saveRoute(TripRouteSaveCommand command) {
		tripRepository.saveRef(
				Trip.builder().name(command.name())
					.attractions(new ArrayList<>())
					.build(),
				command.attractions());
	}
}
