package com.solarsan.whiskyreviewer.brand.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = BrandDTO.BrandDTOBuilder.class)
public class BrandDTO {

    @JsonProperty("id")
    UUID id;

    @JsonProperty("name")
    String name;

    @JsonProperty("country")
    String country;

    public static BrandDTO from(final BrandEntity entity) {
        return new BrandDTO(entity.getId(), entity.getName(), entity.getCountry());
    }
}
