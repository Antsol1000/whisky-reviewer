package com.solarsan.whiskyreviewer.brand.controller;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.exceptions.BrandNotFoundException;
import com.solarsan.whiskyreviewer.brand.service.BrandService;
import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.solarsan.whiskyreviewer.brand.BrandFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class BrandControllerTest {

    @Autowired
    private BrandController controller;

    @MockBean
    private BrandService service;

    @Test
    void getsAllBrands() {
        //given
        doReturn(BRANDS).when(service).getBrands();

        //when
        final List<BrandDTO> brands = controller.getBrands();

        //then
        assertThat(brands).containsExactlyInAnyOrderElementsOf(BRANDS);
    }

    @Test
    void getsBrandById() {
        //given
        doReturn(Optional.of(BRAND_1_DTO)).when(service).getBrand(eq(BRAND_1_ID));

        //when
        final ResponseEntity<BrandDTO> response = controller.getBrand(BRAND_1_ID);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(BRAND_1_DTO);
    }

    @Test
    void whenGettingNonExistingBrandThenReturnEmptyOptional() {
        //given
        doReturn(Optional.empty()).when(service).getBrand(eq(BRAND_1_ID));

        //when
        final ResponseEntity<BrandDTO> response = controller.getBrand(BRAND_1_ID);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createsBrand() {
        //given
        doReturn(IdResponseDTO.builder().id(BRAND_1_ID).build())
                .when(service).createBrand(eq(BRAND_1_NEW_DTO));

        //when
        final ResponseEntity<IdResponseDTO> response = controller.createBrand(BRAND_1_NEW_DTO);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isEqualTo(BRAND_1_ID);
    }

    @Test
    void updatesBrand() {
        //given
        doReturn(IdResponseDTO.builder().id(BRAND_1_ID).build())
                .when(service).updateBrandById(eq(BRAND_1_ID), eq(BRAND_1_NEW_DTO));

        //when
        final ResponseEntity<IdResponseDTO> response = controller.updateBrand(BRAND_1_ID,
                BRAND_1_NEW_DTO);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(BRAND_1_ID);
    }

    @Test
    void whenUpdatingNonExistingBrandThenThrow() {
        //given
        doThrow(BrandNotFoundException.class).when(service).updateBrandById(eq(BRAND_1_ID), eq(BRAND_1_NEW_DTO));

        //when
        final ResponseEntity<IdResponseDTO> response = controller.updateBrand(BRAND_1_ID, BRAND_1_NEW_DTO);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deletesBrand() {
        //when
        final ResponseEntity<Void> response = controller.deleteBrand(BRAND_1_ID);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void whenDeletingNonExistingBrandThenThrow() {
        //given
        doThrow(BrandNotFoundException.class).when(service).deleteBrand(eq(BRAND_1_ID));

        //when
        final ResponseEntity<Void> response = controller.deleteBrand(BRAND_1_ID);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}