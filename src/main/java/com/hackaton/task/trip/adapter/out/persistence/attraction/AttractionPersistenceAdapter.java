package com.hackaton.task.trip.adapter.out.persistence.attraction;

import com.hackaton.task.trip.application.out.AttractionRepository;
import com.hackaton.task.trip.domain.Attraction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttractionPersistenceAdapter implements AttractionRepository {

    private final AttractionJpaRepository repository;
    private final AttractionPersistenceMapper mapper;

    @Override
    public void save(Attraction attraction) {
        var entity = mapper.toEntity(attraction);
        repository.save(entity);
    }

    @Override
    public void delete(Attraction attraction) {
        var entity = mapper.toEntity(attraction);
        repository.delete(entity);
    }

    @Override
    public List<Attraction> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
