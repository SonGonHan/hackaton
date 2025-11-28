package com.hackaton.task.trip.adapter.out.persistence.attraction;

import com.hackaton.task.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionJpaRepository extends JpaRepository<AttractionEntity, Long> {

}
