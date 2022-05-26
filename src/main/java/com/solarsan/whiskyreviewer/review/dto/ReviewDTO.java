package com.solarsan.whiskyreviewer.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solarsan.whiskyreviewer.review.model.ReviewEntity;
import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.whisky.dto.WhiskyDTO;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = ReviewDTO.ReviewDTOBuilder.class)
public class ReviewDTO {

    @JsonProperty("id")
    UUID id;

    @JsonProperty("score")
    float score;

    @JsonProperty("text")
    String text;

    @JsonProperty("reviewer")
    ReviewerDTO reviewer;

    @JsonProperty("whisky")
    WhiskyDTO whisky;

    public static ReviewDTO from(final ReviewEntity entity) {
        return new ReviewDTO(entity.getId(), entity.getScore(), entity.getText(),
                ReviewerDTO.from(entity.getReviewer()), WhiskyDTO.from(entity.getWhisky()));
    }
}
