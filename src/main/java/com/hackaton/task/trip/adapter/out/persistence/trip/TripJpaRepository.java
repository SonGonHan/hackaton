package com.hackaton.task.trip.adapter.out.persistence.trip;

import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionEntity;
import com.hackaton.task.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripJpaRepository extends JpaRepository<TripEntity, Long> {
    List<AttractionEntity> findAttractionsByTripId(Long tripId);
}
