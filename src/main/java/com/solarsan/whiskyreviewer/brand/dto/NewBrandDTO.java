package com.solarsan.whiskyreviewer.brand.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = BrandDTO.BrandDTOBuilder.class)
public class NewBrandDTO {

    @JsonProperty("name")
    String name;

    @JsonProperty("country")
    String country;
}
