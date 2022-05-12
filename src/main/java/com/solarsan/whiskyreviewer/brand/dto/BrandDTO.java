package com.solarsan.whiskyreviewer.brand.dto;

import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class BrandDTO {
    UUID id;
    String name;
    String country;

    public static BrandDTO from(final BrandEntity entity) {
        return new BrandDTO(entity.getId(), entity.getName(), entity.getCountry());
    }
}
