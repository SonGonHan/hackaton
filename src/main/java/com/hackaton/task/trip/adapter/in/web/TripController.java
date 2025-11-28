package com.hackaton.task.trip.adapter.in.web;

import com.hackaton.task.trip.application.in.AttractionUseCase;
import com.hackaton.task.trip.application.in.command.ParseObjectsCommand;
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

	private final AttractionUseCase attractionServiceUseCase;

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

}
