package com.hackaton.task.trip.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Trip {

    private Long id;

    private String name;

    private List<Attraction> attractions;
}
