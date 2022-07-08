package com.solarsan.whiskyreviewer.brand.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NewBrandDTO(
        @JsonProperty("name") String name,
        @JsonProperty("country") String country) {
}
