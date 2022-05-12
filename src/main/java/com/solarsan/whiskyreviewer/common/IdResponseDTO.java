package com.solarsan.whiskyreviewer.common;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class IdResponseDTO {
    UUID id;
}
