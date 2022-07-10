package com.solarsan.whiskyreviewer.whisky.controller;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.whisky.dto.NewWhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.dto.WhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.exceptions.WhiskyNotFoundException;
import com.solarsan.whiskyreviewer.whisky.service.WhiskyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.solarsan.whiskyreviewer.common.ApiVersion.API_1_0;
import static com.solarsan.whiskyreviewer.whisky.endpoints.WhiskyEndpoints.*;

@RestController
@AllArgsConstructor
public class WhiskyController {

    private final WhiskyService whiskyService;

    @PostMapping(value = CREATE_WHISKY, produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> createWhisky(
            @PathVariable(BRAND_ID) final UUID brandId,
            @RequestBody final NewWhiskyDTO whiskyDto) {
        final IdResponseDTO idResponseDTO = whiskyService.createWhisky(brandId, whiskyDto);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath(GET_WHISKY)
                .buildAndExpand(idResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(idResponseDTO);
    }

    @GetMapping(value = GET_WHISKIES, produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<WhiskyDTO> getWhiskies() {
        return whiskyService.getWhiskies();
    }

    @GetMapping(value = GET_WHISKIES_FOR_BRAND, produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<WhiskyDTO> getWhiskiesForBrand(@PathVariable(BRAND_ID) final UUID brandId) {
        return whiskyService.getWhiskiesForBrand(brandId);
    }

    @GetMapping(value = GET_WHISKY, produces = {API_1_0})
    public ResponseEntity<WhiskyDTO> getWhisky(@PathVariable final UUID id) {
        return whiskyService.getWhisky(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = UPDATE_WHISKY, produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> updateWhisky(
            @PathVariable final UUID id,
            @RequestBody final NewWhiskyDTO dto) {
        try {
            return ResponseEntity.ok().body(whiskyService.updateWhiskyById(id, dto));
        } catch (final WhiskyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = DELETE_WHISKY, produces = {API_1_0})
    public ResponseEntity<Void> deleteWhisky(@PathVariable final UUID id) {
        try {
            whiskyService.deleteWhisky(id);
            return ResponseEntity.noContent().build();
        } catch (final WhiskyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
