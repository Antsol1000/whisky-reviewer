package com.solarsan.whiskyreviewer.reviewer.controller;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.NewReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.exceptions.ReviewerNotFoundException;
import com.solarsan.whiskyreviewer.reviewer.service.ReviewerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.solarsan.whiskyreviewer.common.ApiVersion.API_1_0;
import static com.solarsan.whiskyreviewer.reviewer.endpoints.ReviewerEndpoints.*;

@RestController
@AllArgsConstructor
public class ReviewerController {

    private final ReviewerService reviewerService;

    @PostMapping(value = CREATE_REVIEWER, produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> createReviewer(@RequestBody final NewReviewerDTO reviewerDto) {
        final IdResponseDTO id = reviewerService.createReviewer(reviewerDto);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath(GET_REVIEWER)
                .buildAndExpand(id.getId())
                .toUri();
        return ResponseEntity.created(location).body(id);
    }

    @GetMapping(value = GET_REVIEWERS, produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewerDTO> getReviewers() {
        return reviewerService.getReviewers();
    }

    @GetMapping(value = GET_REVIEWER, produces = {API_1_0})
    public ResponseEntity<ReviewerDTO> getReviewer(@PathVariable final UUID id) {
        return reviewerService.getReviewer(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = UPDATE_REVIEWER, produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> updateReviewer(
            @PathVariable final UUID id, @RequestBody final NewReviewerDTO dto) {
        try {
            return ResponseEntity.ok().body(reviewerService.updateReviewerById(id, dto));
        } catch (final ReviewerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = DELETE_REVIEWER, produces = {API_1_0})
    public ResponseEntity<Void> deleteReviewer(@PathVariable final UUID id) {
        try {
            reviewerService.deleteReviewer(id);
            return ResponseEntity.noContent().build();
        } catch (final ReviewerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
