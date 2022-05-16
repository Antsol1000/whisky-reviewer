package com.solarsan.whiskyreviewer.reviewer.controller;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.NewReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.exceptions.ReviewerNotFoundException;
import com.solarsan.whiskyreviewer.reviewer.service.ReviewerService;
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
public class ReviewerController {

    private final ReviewerService reviewerService;

    @PostMapping(value = "/reviewers", produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> createReviewer(@RequestBody final NewReviewerDTO reviewerDto) {
        final IdResponseDTO id = reviewerService.createReviewer(reviewerDto);
        return ResponseEntity.created(URI.create(String.format("/reviewers/%s", id.getId()))).body(id);
    }

    @GetMapping(value = "/reviewers", produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewerDTO> getReviewers() {
        return reviewerService.getReviewers();
    }

    @GetMapping(value = "/reviewers/{id}", produces = {API_1_0})
    public ResponseEntity<ReviewerDTO> getReviewer(@PathVariable final UUID id) {
        return reviewerService.getReviewer(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/reviewers/{id}", produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> updateReviewer(
            @PathVariable final UUID id, @RequestBody final NewReviewerDTO dto) {
        try {
            return ResponseEntity.ok().body(reviewerService.updateReviewerById(id, dto));
        } catch (final ReviewerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/reviewers/{id}", produces = {API_1_0})
    public ResponseEntity<Void> deleteReviewer(@PathVariable final UUID id) {
        try {
            reviewerService.deleteReviewer(id);
            return ResponseEntity.noContent().build();
        } catch (final ReviewerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
