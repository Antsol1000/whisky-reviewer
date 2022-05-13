package com.solarsan.whiskyreviewer.whisky.controller;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.whisky.dto.WhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.dto.NewWhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.exceptions.WhiskyNotFoundException;
import com.solarsan.whiskyreviewer.whisky.service.WhiskyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.solarsan.whiskyreviewer.common.ApiVersion.API_1_0;

@RestController
@AllArgsConstructor
public class WhiskyController {

    private final WhiskyService whiskyService;

    @PostMapping(value = "/whiskies", produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> createWhisky(@RequestBody final NewWhiskyDTO whiskyDto) {
        final IdResponseDTO id = whiskyService.createWhisky(whiskyDto);
        return ResponseEntity.created(URI.create(String.format("/whiskies/%s", id.getId()))).body(id);
    }

    @GetMapping(value = "/whiskies", produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<WhiskyDTO> getWhiskies() {
        return whiskyService.getWhiskies();
    }

    @GetMapping(value = "/whiskies/{id}", produces = {API_1_0})
    public ResponseEntity<WhiskyDTO> getWhisky(@PathVariable final UUID id) {
        return whiskyService.getWhisky(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/whiskies/{id}", produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> updateWhisky(
            @PathVariable final UUID id, @RequestBody final NewWhiskyDTO dto) {
        try {
            return ResponseEntity.ok().body(whiskyService.updateWhiskyById(id, dto));
        } catch (final WhiskyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/whiskies/{id}", produces = {API_1_0})
    public ResponseEntity<Void> deleteWhisky(@PathVariable final UUID id) {
        try {
            whiskyService.deleteWhisky(id);
            return ResponseEntity.noContent().build();
        } catch (final WhiskyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
