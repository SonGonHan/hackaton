package com.hackaton.task.trip.adapter.in.web;

import com.hackaton.task.trip.adapter.in.web.dto.TripRoadRequest;
import com.hackaton.task.trip.adapter.in.web.dto.TripRoadResponse;
import com.hackaton.task.trip.adapter.in.web.dto.TripRouteSaveResponse;
import com.hackaton.task.trip.adapter.in.web.dto.TripRouteSaveRequest;
import com.hackaton.task.trip.application.in.ParseObjectsUseCase;
import com.hackaton.task.trip.application.in.TripRouteGetUseCase;
import com.hackaton.task.trip.application.in.TripRouteSaveUseCase;
import com.hackaton.task.trip.application.in.command.MakeRoadCommand;
import com.hackaton.task.trip.application.in.command.ParseObjectsCommand;
import com.hackaton.task.trip.application.in.command.TripRouteGetCommand;
import com.hackaton.task.trip.application.in.command.TripRouteSaveCommand;
import com.hackaton.task.trip.application.usecase.MakeRoadService;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

	private final ParseObjectsUseCase parseObjectsUseCase;
	private final MakeRoadService makeRoadService;
	private final TripRouteSaveUseCase tripRouteSaveUseCase;
	private final TripRouteGetUseCase tripRouteGetUseCase;

	@Value("${app.res.xml-path}")
	private String OBJECT_XML;

	@PostMapping("/parse_obj")
	public ResponseEntity<Boolean> parseObjects() {
		try {
			parseObjectsUseCase.parseObjectsFromXml(new ParseObjectsCommand(OBJECT_XML));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}

		return ResponseEntity.ok(true);
	}

	@PostMapping("/make-road")
	public TripRoadResponse makeRoad(@RequestBody TripRoadRequest tripRoadRequest) {
		MakeRoadCommand command = new MakeRoadCommand(
				tripRoadRequest.distance(),
				tripRoadRequest.attractions()
		);
		return makeRoadService.makeRoad(command);
	}

	@PostMapping("/save-route")
	public void saveRoute(@RequestBody TripRouteSaveRequest request) {
		tripRouteSaveUseCase.saveRoute(new TripRouteSaveCommand(request.name(), request.attractions()));
	}

	@GetMapping("/get-route/{id}")
	public TripRouteSaveResponse getRoute(@PathVariable Long id) {
		return tripRouteGetUseCase.getTripRoute(new TripRouteGetCommand(id));
	}

	@GetMapping("/")
	public List<Trip> getRoute() {
		return tripRouteGetUseCase.getAllTripRoutes();
	}

}
