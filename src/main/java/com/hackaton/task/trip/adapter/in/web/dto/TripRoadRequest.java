package com.hackaton.task.trip.adapter.in.web.dto;

import com.hackaton.task.trip.domain.Attraction;
import lombok.Builder;

import java.util.List;


public record TripRoadRequest(Integer distance,
                              List<Attraction> attractions) {
}
