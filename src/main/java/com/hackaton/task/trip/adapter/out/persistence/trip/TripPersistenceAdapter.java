package com.hackaton.task.trip.adapter.out.persistence.trip;

import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionEntity;
import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionJpaRepository;
import com.hackaton.task.trip.adapter.out.persistence.attraction.AttractionPersistenceMapper;
import com.hackaton.task.trip.application.out.TripRepository;
import com.hackaton.task.trip.domain.Attraction;
import com.hackaton.task.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TripPersistenceAdapter implements TripRepository {

	private final TripJpaRepository repository;
	private final TripPersistenceMapper tripMapper;
	private final AttractionPersistenceMapper attractionMapper;
	private final AttractionJpaRepository attractionJpaRepository;

	@Override
	public void save(Trip trip) {
		var tripEntity = tripMapper.toEntity(trip);
		repository.save(tripEntity);
	}

	@Override
	public void saveRef(Trip trip, List<Attraction> attractions) {
		var tripEntity = tripMapper.toEntity(trip);

		log.info("Saving trip with attractions: {}", attractions);

		List<AttractionEntity> attractionEntities = new ArrayList<>();

		attractions.forEach(attraction -> {
			attractionEntities.add(attractionJpaRepository.getReferenceById(attraction.getId()));
		});

		tripEntity.setAttractions(attractionEntities);

		repository.save(tripEntity);
	}

	@Override
	public void delete(Trip trip) {
		var tripEntity = tripMapper.toEntity(trip);
		repository.delete(tripEntity);
	}

	@Override
	public Optional<Trip> findById(Long id) {
		return repository.findById(id).map(tripMapper::toDomain);
	}

	@Override
	public List<Attraction> findAttractionsById(Long tripId) {
		return repository.findAttractionsById(tripId).stream()
						 .map(attractionMapper::toDomain)
						 .toList();
	}

}
