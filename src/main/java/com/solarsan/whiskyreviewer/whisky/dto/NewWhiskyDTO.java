package com.solarsan.whiskyreviewer.whisky.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyType;

public record NewWhiskyDTO(
        @JsonProperty("name") String name,
        @JsonProperty("alcohol") int alcohol,
        @JsonProperty("type") WhiskyType type,
        @JsonProperty("age") int age) {

}
