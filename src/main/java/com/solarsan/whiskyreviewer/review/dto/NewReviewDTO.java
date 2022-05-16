package com.solarsan.whiskyreviewer.review.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class NewReviewDTO {
    float score;
    String text;
    UUID whiskyId;
}
