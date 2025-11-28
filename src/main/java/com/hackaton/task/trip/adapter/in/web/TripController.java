package com.hackaton.task.trip.adapter.in.web;

import com.hackaton.task.trip.adapter.in.web.dto.TripRoadRequest;
import com.hackaton.task.trip.adapter.in.web.dto.TripRoadResponse;
import com.hackaton.task.trip.application.in.ParseObjectsUseCase;
import com.hackaton.task.trip.application.in.command.MakeRoadCommand;
import com.hackaton.task.trip.application.in.command.ParseObjectsCommand;
import com.hackaton.task.trip.application.usecase.MakeRoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

	private final ParseObjectsUseCase attractionServiceUseCase;
	private final MakeRoadService makeRoadService;

	@Value("${app.res.xml-path}")
	private String OBJECT_XML;

	@PostMapping("/parse_obj")
	public ResponseEntity<Boolean> parseObjects() {
		try {
			attractionServiceUseCase.parseObjectsFromXml(new ParseObjectsCommand(OBJECT_XML));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}

		return ResponseEntity.ok(true);
	}

	@PostMapping("/make-road")
	public TripRoadResponse makeRoad(TripRoadRequest tripRoadRequest) {
		MakeRoadCommand command = new MakeRoadCommand(
				tripRoadRequest.distance(),
				tripRoadRequest.attractions()
		);
		return makeRoadService.makeRoad(command);
	}

}
