package com.solarsan.whiskyreviewer.review.repository;

import com.solarsan.whiskyreviewer.review.model.ReviewEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReviewRepository extends CrudRepository<ReviewEntity, UUID> {
}
