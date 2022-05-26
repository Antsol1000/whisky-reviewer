package com.solarsan.whiskyreviewer.brand;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.dto.NewBrandDTO;
import com.solarsan.whiskyreviewer.brand.model.BrandEntity;

import java.util.List;
import java.util.UUID;

public class BrandFixtures {

    public static final UUID BRAND_1_ID = UUID.randomUUID();
    public static final String BRAND_1_NAME = "brand_1";
    public static final String BRAND_1_COUNTRY = "country_1";

    public static final UUID BRAND_2_ID = UUID.randomUUID();
    public static final String BRAND_2_NAME = "brand_2";
    public static final String BRAND_2_COUNTRY = "country_2";

    public static final BrandDTO BRAND_1_DTO = BrandDTO
            .builder().id(BRAND_1_ID).name(BRAND_1_NAME).country(BRAND_1_COUNTRY).build();

    public static final NewBrandDTO BRAND_1_NEW_DTO = NewBrandDTO
            .builder().name(BRAND_1_NAME).country(BRAND_1_COUNTRY).build();

    public static final BrandEntity BRAND_1_ENTITY = BrandEntity.from(BRAND_1_DTO);

    public static final BrandDTO BRAND_2_DTO = BrandDTO
            .builder().id(BRAND_2_ID).name(BRAND_2_NAME).country(BRAND_2_COUNTRY).build();

    public static final List<BrandDTO> BRANDS = List.of(BRAND_1_DTO, BRAND_2_DTO);
}
