package com.solarsan.whiskyreviewer.reviewer.service;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.exceptions.ReviewerNotFoundException;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import com.solarsan.whiskyreviewer.reviewer.repository.ReviewerRepositoryManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static com.solarsan.whiskyreviewer.reviewer.ReviewerFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class ReviewerServiceTest {

    @Autowired
    private ReviewerService service;

    @MockBean
    private ReviewerRepositoryManager repositoryManager;

    @Test
    void getsAllReviewers() {
        //given
        doReturn(REVIEWERS).when(repositoryManager).getAll();

        //when
        final List<ReviewerDTO> reviewers = service.getReviewers();

        //then
        assertThat(reviewers).containsExactlyInAnyOrderElementsOf(REVIEWERS);
    }

    @Test
    void getsReviewerById() {
        //given
        doReturn(Optional.of(REVIEWER_1_DTO)).when(repositoryManager).get(eq(REVIEWER_1_ID));

        //when
        final Optional<ReviewerDTO> maybeReviewer = service.getReviewer(REVIEWER_1_ID);

        //then
        assertThat(maybeReviewer).isPresent();
        assertThat(maybeReviewer.get()).isEqualTo(REVIEWER_1_DTO);
    }

    @Test
    void whenGettingNonExistingReviewerThenReturnEmptyOptional() {
        //given
        doReturn(Optional.empty()).when(repositoryManager).get(eq(REVIEWER_1_ID));

        //when
        final Optional<ReviewerDTO> maybeReviewer = service.getReviewer(REVIEWER_1_ID);

        //then
        assertThat(maybeReviewer).isEmpty();
    }

    @Test
    void createsReviewer() {
        //given
        doReturn(REVIEWER_1_ENTITY).when(repositoryManager).save(eq(ReviewerEntity.from(REVIEWER_1_NEW_DTO)));

        //when
        final IdResponseDTO id = service.createReviewer(REVIEWER_1_NEW_DTO);

        //then
        assertThat(id.getId()).isEqualTo(REVIEWER_1_ID);
    }

    @Test
    void updatesReviewer() {
        //given
        doReturn(REVIEWER_1_ENTITY).when(repositoryManager).update(eq(REVIEWER_1_ID), eq(REVIEWER_1_NEW_DTO));

        //when
        final IdResponseDTO id = service.updateReviewerById(REVIEWER_1_ID, REVIEWER_1_NEW_DTO);

        //then
        assertThat(id.getId()).isEqualTo(REVIEWER_1_ID);
    }

    @Test
    void whenUpdatingNonExistingReviewerThenThrow() {
        //given
        doThrow(ReviewerNotFoundException.class).when(repositoryManager).update(eq(REVIEWER_1_ID),
                eq(REVIEWER_1_NEW_DTO));

        //then
        assertThrows(ReviewerNotFoundException.class, () ->
                //when
                service.updateReviewerById(REVIEWER_1_ID, REVIEWER_1_NEW_DTO));
    }

    @Test
    void deletesReviewer() {
        assertDoesNotThrow(() -> service.deleteReviewer(REVIEWER_1_ID));
    }

    @Test
    void whenDeletingNonExistingReviewerThenThrow() {
        //given
        doThrow(ReviewerNotFoundException.class).when(repositoryManager).delete(eq(REVIEWER_1_ID));

        //then
        assertThrows(ReviewerNotFoundException.class, () ->
                //when
                service.deleteReviewer(REVIEWER_1_ID));
    }

}