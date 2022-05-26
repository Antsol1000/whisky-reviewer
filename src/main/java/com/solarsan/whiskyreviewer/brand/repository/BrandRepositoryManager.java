package com.solarsan.whiskyreviewer.brand.repository;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.dto.NewBrandDTO;
import com.solarsan.whiskyreviewer.brand.exceptions.BrandNotFoundException;
import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class BrandRepositoryManager {
    private final BrandRepository brandRepository;

    public BrandEntity save(final BrandEntity entity) {
        return brandRepository.save(entity);
    }

    public List<BrandDTO> getAll() {
        final List<BrandDTO> e = new ArrayList<>();
        brandRepository.findAll().forEach(x -> e.add(BrandDTO.from(x)));
        return e;
    }

    public Optional<BrandDTO> get(final UUID id) {
        return brandRepository.findById(id).map(BrandDTO::from);
    }

    public BrandEntity update(final UUID id, final NewBrandDTO dto) {
        final BrandEntity entity =
                brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException(id.toString()));
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        return brandRepository.save(entity);
    }

    public void delete(final UUID id) {
        brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException(id.toString()));
        brandRepository.deleteById(id);
    }
}
