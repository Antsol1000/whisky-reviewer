package com.solarsan.whiskyreviewer.review.repository;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.review.dto.NewReviewDTO;
import com.solarsan.whiskyreviewer.review.dto.ReviewDTO;
import com.solarsan.whiskyreviewer.review.exceptions.ReviewNotFoundException;
import com.solarsan.whiskyreviewer.review.model.ReviewEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ReviewRepositoryManager {
    private final ReviewRepository reviewRepository;

    public ReviewEntity getEntity(final UUID id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id.toString()));
    }

    public ReviewEntity save(final ReviewEntity entity) {
        return reviewRepository.save(entity);
    }

    public List<ReviewDTO> getAll() {
        final List<ReviewDTO> e = new ArrayList<>();
        reviewRepository.findAll().forEach(x -> e.add(ReviewDTO.from(x)));
        return e;
    }

    public List<ReviewDTO> getAllForWhiskyId(final UUID whiskyId) {
        final List<ReviewDTO> e = new ArrayList<>();
        reviewRepository.findByWhiskyId(whiskyId).forEach(x -> e.add(ReviewDTO.from(x)));
        return e;
    }

    public List<ReviewDTO> getAllForReviewerId(final UUID reviewerId) {
        final List<ReviewDTO> e = new ArrayList<>();
        reviewRepository.findByReviewerId(reviewerId).forEach(x -> e.add(ReviewDTO.from(x)));
        return e;
    }

    public Optional<ReviewDTO> get(final UUID id) {
        return reviewRepository.findById(id).map(ReviewDTO::from);
    }

    public IdResponseDTO update(final UUID id, final NewReviewDTO dto) {
        final ReviewEntity entity =
                reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id.toString()));
        entity.setScore(dto.score());
        entity.setText(dto.text());
        final ReviewEntity saved = reviewRepository.save(entity);
        return IdResponseDTO.builder().id(saved.getId()).build();
    }

    public void delete(final UUID id) {
        reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id.toString()));
        reviewRepository.deleteById(id);
    }
}
