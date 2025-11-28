package com.hackaton.task.trip.adapter.in.web;

import com.hackaton.task.trip.application.out.AttractionRepository;
import com.hackaton.task.trip.domain.Attraction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attractions")
@RequiredArgsConstructor
public class AttractionController {

	private final AttractionRepository attractionRepository;

	@GetMapping("/")
	public ResponseEntity<List<Attraction>> getAllAttractions() {
		return ResponseEntity.ok(attractionRepository.findAll());
	}

}
