package com.solarsan.whiskyreviewer.whisky.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = NewWhiskyDTO.NewWhiskyDTOBuilder.class)
public class NewWhiskyDTO {

    @JsonProperty("name")
    String name;

    @JsonProperty("alcohol")
    int alcohol;

    @JsonProperty("type")
    WhiskyType type;

    @JsonProperty("age")
    int age;
}
