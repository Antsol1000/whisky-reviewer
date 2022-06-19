package com.solarsan.whiskyreviewer.brand.controller;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.dto.NewBrandDTO;
import com.solarsan.whiskyreviewer.common.ControllerIntegrationTestBase;
import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.UUID;

import static com.solarsan.whiskyreviewer.brand.BrandFixtures.*;
import static com.solarsan.whiskyreviewer.brand.endpoints.BrandEndpoints.*;
import static com.solarsan.whiskyreviewer.common.ApiVersion.API_1_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrandControllerIntegrationTest extends ControllerIntegrationTestBase {

    @Test
    void brandIntegrationTest() throws Exception {
        MockHttpServletResponse response;
        // create 1st brand
        response = mockMvc
                .perform(post(CREATE_BRAND).contentType(API_1_0)
                        .content(objectMapper.writeValueAsString(BRAND_1_NEW_DTO)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        final UUID id1 = objectMapper.readValue(response.getContentAsString(), IdResponseDTO.class).getId();

        // create 2nd brand
        response = mockMvc
                .perform(post(CREATE_BRAND).contentType(API_1_0)
                        .content(objectMapper.writeValueAsString(BRAND_2_NEW_DTO)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        final UUID id2 = objectMapper.readValue(response.getContentAsString(), IdResponseDTO.class).getId();

        // get brands
        response = mockMvc
                .perform(get(GET_BRANDS))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        final BrandDTO[] brands = objectMapper.readValue(response.getContentAsString(), BrandDTO[].class);
        assertThat(brands).hasSize(2);

        // get 1st brand by id
        response = mockMvc
                .perform(get(GET_BRAND.replace("{id}", id1.toString())))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        final BrandDTO brand1 = objectMapper.readValue(response.getContentAsString(), BrandDTO.class);
        assertThat(brand1.getName()).isEqualTo(BRAND_1_NAME);
        assertThat(brand1.getCountry()).isEqualTo(BRAND_1_COUNTRY);

        // update 1st brand
        response = mockMvc
                .perform(put(UPDATE_BRAND.replace("{id}", id1.toString()))
                        .contentType(API_1_0).content(objectMapper.writeValueAsString(
                                NewBrandDTO.builder().name(BRAND_1_NAME).country("new country").build())))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        final UUID idAfterUpdate = objectMapper.readValue(response.getContentAsString(), IdResponseDTO.class).getId();
        assertThat(id1).isEqualTo(idAfterUpdate);

        // get 1st brand by id
        response = mockMvc
                .perform(get(GET_BRAND.replace("{id}", id1.toString())))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        final BrandDTO brandAfterUpdate = objectMapper.readValue(response.getContentAsString(), BrandDTO.class);
        assertThat(brandAfterUpdate.getName()).isEqualTo(BRAND_1_NAME);
        assertThat(brandAfterUpdate.getCountry()).isEqualTo("new country");

        // delete 1st brand
        mockMvc
                .perform(delete(DELETE_BRAND.replace("{id}", id1.toString())))
                .andExpect(status().isNoContent());

        // get 1st brand by id
        mockMvc
                .perform(get(GET_BRAND.replace("{id}", id1.toString())))
                .andExpect(status().isNotFound());

        // delete 2nd brand
        mockMvc
                .perform(delete(DELETE_BRAND.replace("{id}", id2.toString())))
                .andExpect(status().isNoContent());

        // get brands
        response = mockMvc
                .perform(get(GET_BRANDS))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        final BrandDTO[] brandsAfterDelete = objectMapper.readValue(response.getContentAsString(), BrandDTO[].class);
        assertThat(brandsAfterDelete).hasSize(0);

    }

}