package com.solarsan.whiskyreviewer.brand;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.dto.NewBrandDTO;
import com.solarsan.whiskyreviewer.brand.model.BrandEntity;

import java.util.UUID;

public class BrandFixtures {

    public static final UUID BRAND_1_ID = UUID.randomUUID();
    public static final String BRAND_1_NAME = "brand_1";
    public static final String BRAND_1_COUNTRY = "country_1";

    public static final UUID BRAND_2_ID = UUID.randomUUID();
    public static final String BRAND_2_NAME = "brand_2";
    public static final String BRAND_2_COUNTRY = "country_2";

    public static final BrandDTO BRAND_1_DTO = new BrandDTO(BRAND_1_ID, BRAND_1_NAME, BRAND_1_COUNTRY);
    public static final BrandEntity BRAND_1_ENTITY = BrandEntity.from(BRAND_1_DTO);
    public static final BrandDTO BRAND_2_DTO = new BrandDTO(BRAND_2_ID, BRAND_2_NAME, BRAND_2_COUNTRY);
    public static final BrandEntity BRAND_2_ENTITY = BrandEntity.from(BRAND_2_DTO);
    public static final NewBrandDTO BRAND_1_NEW_DTO = new NewBrandDTO(BRAND_1_NAME, BRAND_1_COUNTRY);
    public static final NewBrandDTO BRAND_2_NEW_DTO = new NewBrandDTO(BRAND_2_NAME, BRAND_2_COUNTRY);
}
