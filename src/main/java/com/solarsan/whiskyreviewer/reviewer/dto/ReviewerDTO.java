package com.solarsan.whiskyreviewer.reviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;

import java.util.UUID;

public record ReviewerDTO(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name) {

    public static ReviewerDTO from(final ReviewerEntity entity) {
        return new ReviewerDTO(entity.getId(), entity.getName());
    }
}
