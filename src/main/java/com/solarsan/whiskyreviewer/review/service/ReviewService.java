package com.solarsan.whiskyreviewer.review.service;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.review.dto.NewReviewDTO;
import com.solarsan.whiskyreviewer.review.dto.ReviewDTO;
import com.solarsan.whiskyreviewer.review.model.ReviewEntity;
import com.solarsan.whiskyreviewer.review.repository.ReviewRepositoryManager;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
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
public class ReviewService {

    private final ReviewRepositoryManager repositoryManager;

    @Transactional
    public IdResponseDTO createReview(final UUID reviewId, final NewReviewDTO dto) {
        final ReviewerEntity reviewerEntity = ReviewerEntity.builder().build();
        final WhiskyEntity whiskyEntity = WhiskyEntity.builder().build();
        final ReviewEntity entity = ReviewEntity.from(reviewerEntity, whiskyEntity, dto);
        final ReviewEntity saved = repositoryManager.save(entity);
        log.info("Created new review with id {}", saved.getId());
        return IdResponseDTO.builder().id(saved.getId()).build();
    }

    public List<ReviewDTO> getReviews() {
        return repositoryManager.getAll();
    }

    public List<ReviewDTO> getReviewsForWhisky(final UUID whiskyId) {
        return repositoryManager.getAllForWhiskyId(whiskyId);
    }

    public List<ReviewDTO> getReviewsForReviewer(final UUID reviewerId) {
        return repositoryManager.getAllForReviewerId(reviewerId);
    }


    public Optional<ReviewDTO> getReview(final UUID id) {
        return repositoryManager.get(id);
    }

    @Transactional
    public IdResponseDTO updateReviewById(final UUID id, final NewReviewDTO reviewDTO) {
        final IdResponseDTO response = repositoryManager.update(id, reviewDTO);
        log.info("Updated review with id {}", response.getId());
        return response;
    }

    @Transactional
    public void deleteReview(final UUID id) {
        repositoryManager.delete(id);
        log.info("Deleted review with id {}", id);
    }

}
