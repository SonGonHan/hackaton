package com.hackaton.task.trip.application.in.command;

import com.hackaton.task.trip.domain.Attraction;

import java.util.List;

public record MakeRoadCommand(Long distance,
                              List<Attraction> attractions) {
}
