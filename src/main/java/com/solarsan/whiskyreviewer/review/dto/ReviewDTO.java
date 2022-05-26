package com.solarsan.whiskyreviewer.review.dto;

import com.solarsan.whiskyreviewer.review.model.ReviewEntity;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ReviewDTO {
    UUID id;
    float score;
    String text;

    public static ReviewDTO from(final ReviewEntity entity) {
        return new ReviewDTO(entity.getId(), entity.getScore(), entity.getText());
    }
}
