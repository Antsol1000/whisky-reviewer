package com.solarsan.whiskyreviewer.brand.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsan.whiskyreviewer.brand.model.BrandEntity;

import java.util.UUID;

public record BrandDTO(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name,
        @JsonProperty("country") String country) {

    public static BrandDTO from(final BrandEntity entity) {
        return new BrandDTO(entity.getId(), entity.getName(), entity.getCountry());
    }
}
