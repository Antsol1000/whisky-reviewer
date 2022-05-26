package com.solarsan.whiskyreviewer.reviewer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = NewReviewerDTO.NewReviewerDTOBuilder.class)
public class NewReviewerDTO {
    @JsonProperty("name")
    String name;
}
