package com.solarsan.whiskyreviewer.brand.service;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.exceptions.BrandNotFoundException;
import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import com.solarsan.whiskyreviewer.brand.repository.BrandRepositoryManager;
import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static com.solarsan.whiskyreviewer.brand.BrandFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class BrandServiceTest {

    @Autowired
    private BrandService service;

    @MockBean
    private BrandRepositoryManager repositoryManager;

    @Test
    void getsAllBrands() {
        //given
        doReturn(BRANDS).when(repositoryManager).getAll();

        //when
        final List<BrandDTO> brands = service.getBrands();

        //then
        assertThat(brands).containsExactlyInAnyOrderElementsOf(BRANDS);
    }

    @Test
    void getsBrandById() {
        //given
        doReturn(Optional.of(BRAND_1_DTO)).when(repositoryManager).get(eq(BRAND_1_ID));

        //when
        final Optional<BrandDTO> maybeBrand = service.getBrand(BRAND_1_ID);

        //then
        assertThat(maybeBrand).isPresent();
        assertThat(maybeBrand.get()).isEqualTo(BRAND_1_DTO);
    }

    @Test
    void whenGettingNonExistingBrandThenReturnEmptyOptional() {
        //given
        doReturn(Optional.empty()).when(repositoryManager).get(eq(BRAND_1_ID));

        //when
        final Optional<BrandDTO> maybeBrand = service.getBrand(BRAND_1_ID);

        //then
        assertThat(maybeBrand).isEmpty();
    }

    @Test
    void createsBrand() {
        //given
        doReturn(BRAND_1_ENTITY).when(repositoryManager).save(eq(BrandEntity.from(BRAND_1_NEW_DTO)));

        //when
        final IdResponseDTO id = service.createBrand(BRAND_1_NEW_DTO);

        //then
        assertThat(id.getId()).isEqualTo(BRAND_1_ID);
    }

    @Test
    void updatesBrand() {
        //given
        doReturn(BRAND_1_ENTITY).when(repositoryManager).update(eq(BRAND_1_ID), eq(BRAND_1_NEW_DTO));

        //when
        final IdResponseDTO id = service.updateBrandById(BRAND_1_ID, BRAND_1_NEW_DTO);

        //then
        assertThat(id.getId()).isEqualTo(BRAND_1_ID);
    }

    @Test
    void whenUpdatingNonExistingBrandThenThrow() {
        //given
        doThrow(BrandNotFoundException.class).when(repositoryManager).update(eq(BRAND_1_ID), eq(BRAND_1_NEW_DTO));

        //then
        assertThrows(BrandNotFoundException.class, () ->
                //when
                service.updateBrandById(BRAND_1_ID, BRAND_1_NEW_DTO));
    }

    @Test
    void deletesBrand() {
        assertDoesNotThrow(() -> service.deleteBrand(BRAND_1_ID));
    }

    @Test
    void whenDeletingNonExistingBrandThenThrow() {
        //given
        doThrow(BrandNotFoundException.class).when(repositoryManager).delete(eq(BRAND_1_ID));

        //then
        assertThrows(BrandNotFoundException.class, () ->
                //when
                service.deleteBrand(BRAND_1_ID));
    }

}