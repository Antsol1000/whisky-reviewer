package com.solarsan.whiskyreviewer.whisky.repository;

import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WhiskyRepository extends CrudRepository<WhiskyEntity, UUID> {
}
