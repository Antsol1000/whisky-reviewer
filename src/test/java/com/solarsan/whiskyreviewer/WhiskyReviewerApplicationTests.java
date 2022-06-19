package com.solarsan.whiskyreviewer;

import com.solarsan.whiskyreviewer.common.PostgresTestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class WhiskyReviewerApplicationTests extends PostgresTestBase {

    @Test
    void contextLoads() {
        assertTrue(true, "Spring context could not be loaded.");
    }

}
