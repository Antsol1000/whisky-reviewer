package com.solarsan.whiskyreviewer.whisky.endpoints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WhiskyEndpoints {

    public static final String BRAND_ID = "brand_id";

    private static final String WHISKIES = "/whiskies";
    public static final String GET_WHISKIES = WHISKIES;
    private static final String WHISKIES_BY_BRAND = "/brands/{" + BRAND_ID + "}/" + WHISKIES;
    public static final String GET_WHISKIES_FOR_BRAND = WHISKIES_BY_BRAND;
    public static final String CREATE_WHISKY = WHISKIES_BY_BRAND;
    private static final String WHISKIES_WITH_ID = WHISKIES + "/{id}";
    public static final String GET_WHISKY = WHISKIES_WITH_ID;
    public static final String UPDATE_WHISKY = WHISKIES_WITH_ID;
    public static final String DELETE_WHISKY = WHISKIES_WITH_ID;
}
