package com.solarsan.whiskyreviewer.whisky.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyType;

import java.util.UUID;

public record WhiskyDTO(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name,
        @JsonProperty("alcohol") int alcohol,
        @JsonProperty("type") WhiskyType type,
        @JsonProperty("age") int age,
        @JsonProperty("brand") BrandDTO brand) {

    public static WhiskyDTO from(final WhiskyEntity entity) {
        return new WhiskyDTO(entity.getId(), entity.getName(), entity.getAlcohol(),
                entity.getType(), entity.getAge(), BrandDTO.from(entity.getBrand()));
    }
}
