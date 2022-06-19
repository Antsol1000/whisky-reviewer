package com.solarsan.whiskyreviewer.brand.endpoints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandEndpoints {

    private static final String BRANDS = "/brands";
    private static final String BRANDS_WITH_ID = BRANDS + "/{id}";

    public static final String GET_BRANDS = BRANDS;
    public static final String CREATE_BRAND = BRANDS;
    public static final String GET_BRAND = BRANDS_WITH_ID;
    public static final String UPDATE_BRAND = BRANDS_WITH_ID;
    public static final String DELETE_BRAND = BRANDS_WITH_ID;
}
