package com.solarsan.whiskyreviewer.reviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

public record ReviewerDTO(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name) {

    public static ReviewerDTO from(final ReviewerEntity entity) {
        return new ReviewerDTO(entity.getId(), entity.getName());
    }
}
