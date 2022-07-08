package com.solarsan.whiskyreviewer.reviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NewReviewerDTO(
        @JsonProperty("name") String name) {
}
