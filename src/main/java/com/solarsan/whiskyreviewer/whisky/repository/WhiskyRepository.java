package com.solarsan.whiskyreviewer.whisky.repository;

import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface WhiskyRepository extends CrudRepository<WhiskyEntity, UUID> {
    @Query("from Whisky w where w.brand.id=:brand_id")
    Iterable<WhiskyEntity> findByBrandId(@Param("brand_id") final UUID brandId);
}
