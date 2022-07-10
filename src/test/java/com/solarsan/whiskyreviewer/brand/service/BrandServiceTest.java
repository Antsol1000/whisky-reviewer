package com.solarsan.whiskyreviewer.brand.service;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.exceptions.BrandNotFoundException;
import com.solarsan.whiskyreviewer.brand.repository.BrandRepositoryManager;
import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.common.PostgresTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.solarsan.whiskyreviewer.brand.BrandFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BrandServiceTest extends PostgresTestBase {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepositoryManager brandRepositoryManager;

    @Test
    void getsAllBrands() {
        //given
        brandRepositoryManager.save(BRAND_1_ENTITY);
        brandRepositoryManager.save(BRAND_2_ENTITY);

        //when
        final List<BrandDTO> brands = brandService.getBrands();

        //then
        assertThat(brands).containsExactlyInAnyOrder(BRAND_1_DTO, BRAND_2_DTO);
    }

    @Test
    void getsBrandById() {
        //given
        brandRepositoryManager.save(BRAND_1_ENTITY);
        brandRepositoryManager.save(BRAND_2_ENTITY);

        //when
        final Optional<BrandDTO> maybeBrand = brandService.getBrand(BRAND_1_ID);

        //then
        assertThat(maybeBrand).isPresent();
        assertThat(maybeBrand.get()).isEqualTo(BRAND_1_DTO);
    }

    @Test
    void whenGettingNonExistingBrandThenReturnEmptyOptional() {
        //given
        brandRepositoryManager.save(BRAND_2_ENTITY);

        //when
        final Optional<BrandDTO> maybeBrand = brandService.getBrand(BRAND_1_ID);

        //then
        assertThat(maybeBrand).isEmpty();
    }

    @Test
    void createsBrand() {
        //when
        final IdResponseDTO idResponseDTO = brandService.createBrand(BRAND_1_NEW_DTO);

        //then
        final Optional<BrandDTO> maybeBrand = brandService.getBrand(idResponseDTO.id());
        assertThat(maybeBrand).isPresent();
        assertThat(maybeBrand.get().name()).isEqualTo(BRAND_1_NAME);
        assertThat(maybeBrand.get().country()).isEqualTo(BRAND_1_COUNTRY);
    }

    @Test
    void updatesBrand() {
        //given
        brandRepositoryManager.save(BRAND_1_ENTITY);

        //when
        final IdResponseDTO idResponseDTO = brandService.updateBrandById(BRAND_1_ID, BRAND_2_NEW_DTO);

        //then
        final Optional<BrandDTO> maybeBrand = brandService.getBrand(idResponseDTO.id());
        assertThat(maybeBrand).isPresent();
        assertThat(maybeBrand.get().name()).isEqualTo(BRAND_2_NAME);
        assertThat(maybeBrand.get().country()).isEqualTo(BRAND_2_COUNTRY);
    }

    @Test
    void whenUpdatingNonExistingBrandThenThrow() {
        //given
        brandRepositoryManager.save(BRAND_1_ENTITY);

        //then
        assertThrows(BrandNotFoundException.class, () ->
                //when
                brandService.updateBrandById(BRAND_2_ID, BRAND_2_NEW_DTO));
    }

    @Test
    void deletesBrand() {
        //given
        brandRepositoryManager.save(BRAND_1_ENTITY);
        brandRepositoryManager.save(BRAND_2_ENTITY);

        //when
        brandService.deleteBrand(BRAND_1_ID);

        //then
        assertThat(brandService.getBrands()).containsExactlyInAnyOrder(BRAND_2_DTO);
    }

    @Test
    void whenDeletingNonExistingBrandThenThrow() {
        //given
        brandRepositoryManager.save(BRAND_1_ENTITY);

        //then
        assertThrows(BrandNotFoundException.class, () ->
                //when
                brandService.deleteBrand(BRAND_2_ID));
    }

}
