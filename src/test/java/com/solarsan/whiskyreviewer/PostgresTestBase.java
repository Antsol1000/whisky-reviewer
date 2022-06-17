package com.solarsan.whiskyreviewer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = WhiskyReviewerApplication.class,
        initializers = PostgresTestBase.CustomInitializer.class)
@ComponentScan(basePackages = "com.solarsan.whiskyreviewer")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class PostgresTestBase {

    private static final String DOCKER_IMAGE_NAME = "postgres:12-alpine";

    private static final PostgreSQLContainer<?> DB_CONTAINER = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME);

    private static final List<String> TABLES = List.of("brand", "reviewer", "whisky", "review");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void cleanUpDb() {
        TABLES.forEach(table ->
                jdbcTemplate.update(String.format("delete from %s;", table)));
    }

    public static class CustomInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(final ConfigurableApplicationContext applicationContext) {
            if (Arrays.stream(applicationContext.getEnvironment().getActiveProfiles())
                    .noneMatch(x -> x.equals("local"))) {
                DB_CONTAINER.start();
                System.setProperty("spring.datasource.url", DB_CONTAINER.getJdbcUrl());
                System.setProperty("spring.datasource.username", DB_CONTAINER.getUsername());
                System.setProperty("spring.datasource.password", DB_CONTAINER.getPassword());
            }
        }
    }
}
