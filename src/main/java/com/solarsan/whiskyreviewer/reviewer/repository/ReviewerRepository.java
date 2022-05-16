package com.solarsan.whiskyreviewer.reviewer.repository;

import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReviewerRepository extends CrudRepository<ReviewerEntity, UUID> {
}
