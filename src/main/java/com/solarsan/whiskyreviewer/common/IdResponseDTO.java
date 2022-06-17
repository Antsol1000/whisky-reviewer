package com.solarsan.whiskyreviewer.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = IdResponseDTO.IdResponseDTOBuilder.class)
public class IdResponseDTO {

    @JsonProperty("id")
    UUID id;
}
