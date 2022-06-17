package com.solarsan.whiskyreviewer.brand.controller;

import com.solarsan.whiskyreviewer.ControllerTestBase;
import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.exceptions.BrandNotFoundException;
import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.solarsan.whiskyreviewer.brand.BrandFixtures.*;
import static com.solarsan.whiskyreviewer.common.ApiVersion.API_1_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrandControllerTest extends ControllerTestBase<BrandController> {

    @Test
    void getsAllBrands() throws Exception {
        //given
        doReturn(List.of(BRAND_1_DTO, BRAND_2_DTO)).when(brandService).getBrands();

        //when
        final ResultActions result = mockMvc.perform(get("/brands"));

        //then
        final MockHttpServletResponse response = result
                .andExpect(status().isOk()).andReturn().getResponse();
        final BrandDTO[] brands = objectMapper.readValue(response.getContentAsString(), BrandDTO[].class);
        assertThat(brands).containsExactlyInAnyOrder(BRAND_1_DTO, BRAND_2_DTO);
    }

    @Test
    void getsBrandById() throws Exception {
        //given
        doReturn(Optional.of(BRAND_1_DTO)).when(brandService).getBrand(eq(BRAND_1_ID));

        //when
        final ResultActions result = mockMvc
                .perform(get(String.format("/brands/%s", BRAND_1_ID)));

        //then
        final MockHttpServletResponse response = result
                .andExpect(status().isOk()).andReturn().getResponse();
        final BrandDTO brand = objectMapper.readValue(response.getContentAsString(), BrandDTO.class);
        assertThat(brand).isEqualTo(BRAND_1_DTO);
    }

    @Test
    void whenGettingNonExistingBrandThenReturnNotFound() throws Exception {
        //given
        doReturn(Optional.empty()).when(brandService).getBrand(eq(BRAND_1_ID));

        //when & then
        mockMvc.perform(get(String.format("/brands/%s", BRAND_1_ID)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createsBrand() throws Exception {
        //given
        doReturn(IdResponseDTO.builder().id(BRAND_1_ID).build())
                .when(brandService).createBrand(eq(BRAND_1_NEW_DTO));

        //when
        final ResultActions result = mockMvc
                .perform(post("/brands")
                        .contentType(API_1_0)
                        .content(objectMapper.writeValueAsString(BRAND_1_NEW_DTO)));

        //then
        final MockHttpServletResponse response = result
                .andExpect(status().isCreated()).andReturn().getResponse();
        assertThat(response.getHeader("location")).isEqualTo(String.format("/brands/%s", BRAND_1_ID));
        final UUID id = objectMapper.readValue(response.getContentAsString(), IdResponseDTO.class).getId();
        assertThat(id).isEqualTo(BRAND_1_ID);
    }

    @Test
    void updatesBrand() throws Exception {
        //given
        doReturn(IdResponseDTO.builder().id(BRAND_1_ID).build())
                .when(brandService).updateBrandById(eq(BRAND_1_ID), eq(BRAND_1_NEW_DTO));

        //when
        final ResultActions result = mockMvc.perform(put(String.format("/brands/%s", BRAND_1_ID))
                .contentType(API_1_0)
                .content(objectMapper.writeValueAsString(BRAND_1_NEW_DTO)));

        //then
        final MockHttpServletResponse response =
                result.andExpect(status().isOk()).andReturn().getResponse();
        final UUID id = objectMapper.readValue(response.getContentAsString(), IdResponseDTO.class).getId();
        assertThat(id).isEqualTo(BRAND_1_ID);
    }

    @Test
    void whenUpdatingNonExistingBrandThenReturnNotFound() throws Exception {
        //given
        doThrow(BrandNotFoundException.class).when(brandService).updateBrandById(eq(BRAND_1_ID), eq(BRAND_1_NEW_DTO));

        //when & then
        mockMvc.perform(put(String.format("/brands/%s", BRAND_1_ID))
                        .contentType(API_1_0)
                        .content(objectMapper.writeValueAsString(BRAND_1_NEW_DTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletesBrand() throws Exception {
        //when & then
        mockMvc.perform(delete(String.format("/brands/%s", BRAND_1_ID))
                        .contentType(API_1_0)
                        .content(objectMapper.writeValueAsString(BRAND_1_NEW_DTO)))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDeletingNonExistingBrandThenReturnNotFound() throws Exception {
        //given
        doThrow(BrandNotFoundException.class).when(brandService).deleteBrand(eq(BRAND_1_ID));

        //when & then
        mockMvc.perform(delete(String.format("/brands/%s", BRAND_1_ID)))
                .andExpect(status().isNotFound());
    }

}