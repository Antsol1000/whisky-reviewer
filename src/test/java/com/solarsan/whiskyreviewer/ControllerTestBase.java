package com.solarsan.whiskyreviewer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solarsan.whiskyreviewer.brand.service.BrandService;
import com.solarsan.whiskyreviewer.review.service.ReviewService;
import com.solarsan.whiskyreviewer.reviewer.service.ReviewerService;
import com.solarsan.whiskyreviewer.whisky.service.WhiskyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public abstract class ControllerTestBase<C> {

    protected C controller;

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected BrandService brandService;
    @MockBean
    protected WhiskyService whiskyService;
    @MockBean
    protected ReviewerService reviewerService;
    @MockBean
    protected ReviewService reviewService;

}
