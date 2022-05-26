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

    public ReviewEntity save(final ReviewEntity entity) {
        return reviewRepository.save(entity);
    }

    public List<ReviewDTO> getAll() {
        final List<ReviewDTO> e = new ArrayList<>();
        reviewRepository.findAll().forEach(x -> e.add(ReviewDTO.from(x)));
        return e;
    }

    public List<ReviewDTO> getAllForWhiskyId(final UUID whiskyId) {
        return List.of();
    }

    public List<ReviewDTO> getAllForReviewerId(final UUID reviewerId) {
        return List.of();
    }

    public Optional<ReviewDTO> get(final UUID id) {
        return reviewRepository.findById(id).map(ReviewDTO::from);
    }

    public IdResponseDTO update(final UUID id, final NewReviewDTO dto) {
        final ReviewEntity entity =
                reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id.toString()));
        entity.setScore(dto.getScore());
        entity.setText(dto.getText());
        final ReviewEntity saved = reviewRepository.save(entity);
        return IdResponseDTO.builder().id(saved.getId()).build();
    }

    public void delete(final UUID id) {
        reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id.toString()));
        reviewRepository.deleteById(id);
    }
}
