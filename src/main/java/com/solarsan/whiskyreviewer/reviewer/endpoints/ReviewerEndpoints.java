package com.solarsan.whiskyreviewer.reviewer.endpoints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewerEndpoints {

    private static final String REVIEWERS = "/reviewers";
    private static final String REVIEWERS_WITH_ID = REVIEWERS + "/{id}";

    public static final String GET_REVIEWERS = REVIEWERS;
    public static final String CREATE_REVIEWER = REVIEWERS;
    public static final String GET_REVIEWER = REVIEWERS_WITH_ID;
    public static final String UPDATE_REVIEWER = REVIEWERS_WITH_ID;
    public static final String DELETE_REVIEWER = REVIEWERS_WITH_ID;
}
