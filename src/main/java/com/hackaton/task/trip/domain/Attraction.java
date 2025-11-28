package com.hackaton.task.trip.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attraction {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
}
