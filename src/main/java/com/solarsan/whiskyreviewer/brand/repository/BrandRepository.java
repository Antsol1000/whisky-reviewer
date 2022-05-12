package com.solarsan.whiskyreviewer.brand.repository;

import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BrandRepository extends CrudRepository<BrandEntity, UUID> {
}
