package com.solarsan.whiskyreviewer.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = NewReviewDTO.NewReviewDTOBuilder.class)
public class NewReviewDTO {

    @JsonProperty("score")
    float score;

    @JsonProperty("text")
    String text;

    @JsonProperty("whisky_id")
    UUID whiskyId;
}
