package com.solarsan.whiskyreviewer.whisky.dto;

import com.solarsan.whiskyreviewer.whisky.model.WhiskyType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewWhiskyDTO {
    String name;
    int alcohol;
    WhiskyType type;
    int age;
}
