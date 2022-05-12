package com.solarsan.whiskyreviewer.brand.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewBrandDTO {
    String name;
    String country;
}
