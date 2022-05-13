package com.solarsan.whiskyreviewer.whisky.dto;

import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyType;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class WhiskyDTO {
    UUID id;
    String name;
    int alcohol;
    WhiskyType type;
    int age;

    public static WhiskyDTO from(final WhiskyEntity entity) {
        return new WhiskyDTO(entity.getId(), entity.getName(), entity.getAlcohol(),
                entity.getType(), entity.getAge());
    }
}
