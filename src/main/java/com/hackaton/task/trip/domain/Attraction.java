package com.hackaton.task.trip.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
//@Builder
@AllArgsConstructor
public class Attraction {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private boolean required;
}
