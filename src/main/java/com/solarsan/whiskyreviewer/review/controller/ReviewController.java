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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.solarsan.whiskyreviewer.common.ApiVersion.API_1_0;
import static com.solarsan.whiskyreviewer.review.endpoints.ReviewEndpoints.*;

@RestController
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = CREATE_REVIEW, produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> createReview(
            @PathVariable(REVIEWER_ID) final UUID reviewerId,
            @RequestBody final NewReviewDTO reviewDto) {
        final IdResponseDTO id = reviewService.createReview(reviewerId, reviewDto);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath(GET_REVIEW)
                .buildAndExpand(id.getId())
                .toUri();
        return ResponseEntity.created(location).body(id);
    }

    @GetMapping(value = GET_REVIEWS, produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping(value = GET_REVIEWS_FOR_WHISKY, produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getReviewsForWhisky(
            @PathVariable(WHISKY_ID) final UUID whiskyId) {
        return reviewService.getReviewsForWhisky(whiskyId);
    }

    @GetMapping(value = GET_REVIEWS_FOR_REVIEWER, produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getReviewsForReviewer(
            @PathVariable(REVIEWER_ID) final UUID reviewerId) {
        return reviewService.getReviewsForReviewer(reviewerId);
    }

    @GetMapping(value = GET_REVIEW, produces = {API_1_0})
    public ResponseEntity<ReviewDTO> getReview(@PathVariable final UUID id) {
        return reviewService.getReview(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = UPDATE_REVIEW, produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> updateReview(
            @PathVariable final UUID id, @RequestBody final NewReviewDTO dto) {
        try {
            return ResponseEntity.ok().body(reviewService.updateReviewById(id, dto));
        } catch (final ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = DELETE_REVIEW, produces = {API_1_0})
    public ResponseEntity<Void> deleteReview(@PathVariable final UUID id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (final ReviewNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
