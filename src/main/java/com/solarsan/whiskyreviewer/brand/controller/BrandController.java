package com.solarsan.whiskyreviewer.brand.controller;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.dto.NewBrandDTO;
import com.solarsan.whiskyreviewer.brand.exceptions.BrandNotFoundException;
import com.solarsan.whiskyreviewer.brand.service.BrandService;
import com.solarsan.whiskyreviewer.common.IdResponseDTO;
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
public class BrandController {

    private final BrandService brandService;

    @PostMapping(value = "/brands", produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> createBrand(@RequestBody final NewBrandDTO brandDto) {
        final IdResponseDTO id = brandService.createBrand(brandDto);
        return ResponseEntity.created(URI.create(String.format("/brands/%s", id.getId()))).body(id);
    }

    @GetMapping(value = "/brands", produces = {API_1_0})
    @ResponseStatus(HttpStatus.OK)
    public List<BrandDTO> getBrands() {
        return brandService.getBrands();
    }

    @GetMapping(value = "/brands/{id}", produces = {API_1_0})
    public ResponseEntity<BrandDTO> getBrand(@PathVariable final UUID id) {
        return brandService.getBrand(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/brands/{id}", produces = {API_1_0})
    public ResponseEntity<IdResponseDTO> updateBrand(
            @PathVariable final UUID id, @RequestBody final NewBrandDTO dto) {
        try {
            return ResponseEntity.ok().body(brandService.updateBrandById(id, dto));
        } catch (final BrandNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/brands/{id}", produces = {API_1_0})
    public ResponseEntity<Void> deleteBrand(@PathVariable final UUID id) {
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.noContent().build();
        } catch (final BrandNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
