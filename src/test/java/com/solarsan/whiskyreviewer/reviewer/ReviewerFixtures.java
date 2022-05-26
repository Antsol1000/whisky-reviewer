package com.solarsan.whiskyreviewer.reviewer;

import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.NewReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;

import java.util.List;
import java.util.UUID;

public class ReviewerFixtures {

    public static final UUID REVIEWER_1_ID = UUID.randomUUID();
    public static final String REVIEWER_1_NAME = "reviewer_1";

    public static final UUID REVIEWER_2_ID = UUID.randomUUID();
    public static final String REVIEWER_2_NAME = "reviewer_2";

    public static final ReviewerDTO REVIEWER_1_DTO = ReviewerDTO
            .builder().id(REVIEWER_1_ID).name(REVIEWER_1_NAME).build();

    public static final NewReviewerDTO REVIEWER_1_NEW_DTO = NewReviewerDTO
            .builder().name(REVIEWER_1_NAME).build();

    public static final ReviewerEntity REVIEWER_1_ENTITY = ReviewerEntity.from(REVIEWER_1_DTO);

    public static final ReviewerDTO REVIEWER_2_DTO = ReviewerDTO
            .builder().id(REVIEWER_2_ID).name(REVIEWER_2_NAME).build();

    public static final List<ReviewerDTO> REVIEWERS = List.of(REVIEWER_1_DTO, REVIEWER_2_DTO);
}
