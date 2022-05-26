package com.solarsan.whiskyreviewer.whisky.repository;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.whisky.dto.NewWhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.dto.WhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.exceptions.WhiskyNotFoundException;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class WhiskyRepositoryManager {
    private final WhiskyRepository whiskyRepository;

    public WhiskyEntity getEntity(final UUID id) {
        return whiskyRepository.findById(id).orElseThrow(() -> new WhiskyNotFoundException(id.toString()));
    }

    public WhiskyEntity save(final WhiskyEntity entity) {
        return whiskyRepository.save(entity);
    }

    public List<WhiskyDTO> getAll() {
        final List<WhiskyDTO> e = new ArrayList<>();
        whiskyRepository.findAll().forEach(x -> e.add(WhiskyDTO.from(x)));
        return e;
    }

    public List<WhiskyDTO> getAllForBrand(final UUID brandId) {
        final List<WhiskyDTO> e = new ArrayList<>();
        whiskyRepository.findByBrandId(brandId).forEach(x -> e.add(WhiskyDTO.from(x)));
        return e;
    }

    public Optional<WhiskyDTO> get(final UUID id) {
        return whiskyRepository.findById(id).map(WhiskyDTO::from);
    }

    public IdResponseDTO update(final UUID id, final NewWhiskyDTO dto) {
        final WhiskyEntity entity =
                whiskyRepository.findById(id).orElseThrow(() -> new WhiskyNotFoundException(id.toString()));
        entity.setName(dto.getName());
        entity.setAlcohol(dto.getAlcohol());
        entity.setType(dto.getType());
        entity.setAge(dto.getAge());
        final WhiskyEntity saved = whiskyRepository.save(entity);
        return IdResponseDTO.builder().id(saved.getId()).build();
    }

    public void delete(final UUID id) {
        whiskyRepository.findById(id).orElseThrow(() -> new WhiskyNotFoundException(id.toString()));
        whiskyRepository.deleteById(id);
    }
}
