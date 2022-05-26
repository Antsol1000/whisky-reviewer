package com.solarsan.whiskyreviewer.review.repository;

import com.solarsan.whiskyreviewer.review.model.ReviewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ReviewRepository extends CrudRepository<ReviewEntity, UUID> {
    @Query("from Review r where r.reviewer.id=:reviewer_id")
    Iterable<ReviewEntity> findByReviewerId(@Param("reviewer_id") final UUID reviewerId);

    @Query("from Review r where r.whisky.id=:whisky_id")
    Iterable<ReviewEntity> findByWhiskyId(@Param("whisky_id") final UUID reviewerId);
}
