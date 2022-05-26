package com.solarsan.whiskyreviewer.reviewer.controller;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.exceptions.ReviewerNotFoundException;
import com.solarsan.whiskyreviewer.reviewer.service.ReviewerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.solarsan.whiskyreviewer.reviewer.ReviewerFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class ReviewerControllerTest {

    @Autowired
    private ReviewerController controller;

    @MockBean
    private ReviewerService service;

    @Test
    void getsAllReviewers() {
        //given
        doReturn(REVIEWERS).when(service).getReviewers();

        //when
        final List<ReviewerDTO> reviewers = controller.getReviewers();

        //then
        assertThat(reviewers).containsExactlyInAnyOrderElementsOf(REVIEWERS);
    }

    @Test
    void getsReviewerById() {
        //given
        doReturn(Optional.of(REVIEWER_1_DTO)).when(service).getReviewer(eq(REVIEWER_1_ID));

        //when
        final ResponseEntity<ReviewerDTO> response = controller.getReviewer(REVIEWER_1_ID);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(REVIEWER_1_DTO);
    }

    @Test
    void whenGettingNonExistingReviewerThenReturnEmptyOptional() {
        //given
        doReturn(Optional.empty()).when(service).getReviewer(eq(REVIEWER_1_ID));

        //when
        final ResponseEntity<ReviewerDTO> response = controller.getReviewer(REVIEWER_1_ID);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createsReviewer() {
        //given
        doReturn(IdResponseDTO.builder().id(REVIEWER_1_ID).build())
                .when(service).createReviewer(eq(REVIEWER_1_NEW_DTO));

        //when
        final ResponseEntity<IdResponseDTO> response = controller.createReviewer(REVIEWER_1_NEW_DTO);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isEqualTo(REVIEWER_1_ID);
    }

    @Test
    void updatesReviewer() {
        //given
        doReturn(IdResponseDTO.builder().id(REVIEWER_1_ID).build())
                .when(service).updateReviewerById(eq(REVIEWER_1_ID), eq(REVIEWER_1_NEW_DTO));

        //when
        final ResponseEntity<IdResponseDTO> response = controller.updateReviewer(REVIEWER_1_ID,
                REVIEWER_1_NEW_DTO);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(REVIEWER_1_ID);
    }

    @Test
    void whenUpdatingNonExistingReviewerThenThrow() {
        //given
        doThrow(ReviewerNotFoundException.class).when(service).updateReviewerById(eq(REVIEWER_1_ID),
                eq(REVIEWER_1_NEW_DTO));

        //when
        final ResponseEntity<IdResponseDTO> response = controller.updateReviewer(REVIEWER_1_ID, REVIEWER_1_NEW_DTO);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deletesReviewer() {
        //when
        final ResponseEntity<Void> response = controller.deleteReviewer(REVIEWER_1_ID);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void whenDeletingNonExistingReviewerThenThrow() {
        //given
        doThrow(ReviewerNotFoundException.class).when(service).deleteReviewer(eq(REVIEWER_1_ID));

        //when
        final ResponseEntity<Void> response = controller.deleteReviewer(REVIEWER_1_ID);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}