package com.solarsan.whiskyreviewer.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record IdResponseDTO(
        @JsonProperty("id") UUID id) {

}
