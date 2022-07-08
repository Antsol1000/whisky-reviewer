package com.solarsan.whiskyreviewer.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record NewReviewDTO(
        @JsonProperty("score") float score,
        @JsonProperty("text") String text,
        @JsonProperty("whisky_id") UUID whiskyId) {

}
