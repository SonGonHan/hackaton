package com.hackaton.task.trip.adapter.out.persistence.attraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionJpaRepository extends JpaRepository<AttractionEntity, Long> {
    
}
