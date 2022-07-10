package com.solarsan.whiskyreviewer.review.endpoints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewEndpoints {

    public static final String REVIEWER_ID = "reviewer_id";
    public static final String WHISKY_ID = "whisky_id";

    private static final String REVIEWS = "/reviews";
    public static final String GET_REVIEWS = REVIEWS;
    private static final String REVIEWS_BY_REVIEWER = "/reviewers/{" + REVIEWER_ID + "}/" + REVIEWS;
    public static final String GET_REVIEWS_FOR_REVIEWER = REVIEWS_BY_REVIEWER;
    public static final String CREATE_REVIEW = REVIEWS_BY_REVIEWER;
    private static final String REVIEWS_BY_WHISKY = "/whiskies/{" + WHISKY_ID + "}/" + REVIEWS;
    public static final String GET_REVIEWS_FOR_WHISKY = REVIEWS_BY_WHISKY;
    private static final String REVIEWS_WITH_ID = REVIEWS + "/{id}";
    public static final String GET_REVIEW = REVIEWS_WITH_ID;
    public static final String UPDATE_REVIEW = REVIEWS_WITH_ID;
    public static final String DELETE_REVIEW = REVIEWS_WITH_ID;
}
