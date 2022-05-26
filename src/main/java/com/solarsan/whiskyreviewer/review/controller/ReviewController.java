package com.solarsan.whiskyreviewer.review.controller;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.review.dto.NewReviewDTO;
import com.solarsan.whiskyreviewer.review.dto.ReviewDTO;
import com.solarsan.whiskyreviewer.review.exceptions.ReviewNotFoundException;
import com.solarsan.whiskyreviewer.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.solarsan.whiskyreviewer.common.ApiVersion.API_1_0;

@RestController
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/reviewer/{reviewer_id}/reviews", produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> createReview(
            @PathVariable("reviewer_id") final UUID reviewerId,
            @RequestBody final NewReviewDTO reviewDto) {
        final IdResponseDTO id = reviewService.createReview(reviewerId, reviewDto);
        return ResponseEntity.created(URI.create(String.format("/reviews/%s", id.getId()))).body(id);
    }

    @GetMapping(value = "/reviews", produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping(value = "/whisky/{whisky_id}/reviews", produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getReviewsForWhisky(
            @PathVariable("whisky_id") final UUID whiskyId) {
        return reviewService.getReviewsForWhisky(whiskyId);
    }

    @GetMapping(value = "/reviewer/{reviewer_id}/reviews", produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getReviewsForReviewer(
            @PathVariable("reviewer_id") final UUID reviewerId) {
        return reviewService.getReviewsForReviewer(reviewerId);
    }

    @GetMapping(value = "/reviews/{id}", produces = {API_1_0})
    public ResponseEntity<ReviewDTO> getReview(@PathVariable final UUID id) {
        return reviewService.getReview(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/reviews/{id}", produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> updateReview(
            @PathVariable final UUID id, @RequestBody final NewReviewDTO dto) {
        try {
            return ResponseEntity.ok().body(reviewService.updateReviewById(id, dto));
        } catch (final ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/reviews/{id}", produces = {API_1_0})
    public ResponseEntity<Void> deleteReview(@PathVariable final UUID id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (final ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
