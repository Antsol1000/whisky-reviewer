package com.solarsan.whiskyreviewer.reviewer.dto;

import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ReviewerDTO {
    UUID id;
    String name;

    public static ReviewerDTO from(final ReviewerEntity entity) {
        return new ReviewerDTO(entity.getId(), entity.getName());
    }
}
