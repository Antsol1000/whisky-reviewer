package com.solarsan.whiskyreviewer.brand.service;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.dto.NewBrandDTO;
import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import com.solarsan.whiskyreviewer.brand.repository.BrandRepositoryManager;
import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepositoryManager repositoryManager;

    @Transactional
    public IdResponseDTO createBrand(final NewBrandDTO dto) {
        final BrandEntity entity = BrandEntity.from(dto);
        final BrandEntity saved = repositoryManager.save(entity);
        log.info("Created new brand {} with id {}", saved.getName(), saved.getId());
        return IdResponseDTO.builder().id(saved.getId()).build();
    }

    public List<BrandDTO> getBrands() {
        return repositoryManager.getAll();
    }

    public Optional<BrandDTO> getBrand(final UUID id) {
        return repositoryManager.get(id);
    }

    @Transactional
    public IdResponseDTO updateBrandById(final UUID id, final NewBrandDTO brandDTO) {
        final IdResponseDTO response = repositoryManager.update(id, brandDTO);
        log.info("Updated brand with id {}", response.getId());
        return response;
    }

    @Transactional
    public void deleteBrand(final UUID id) {
        repositoryManager.delete(id);
        log.info("Deleted brand with id {}", id);
    }

}
