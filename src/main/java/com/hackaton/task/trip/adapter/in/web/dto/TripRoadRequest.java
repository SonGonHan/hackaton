package com.hackaton.task.trip.adapter.in.web.dto;

import com.hackaton.task.trip.domain.Attraction;

import java.util.List;

public record TripRoadRequest(Long distance,
                              List<Attraction> attractions) {
}
