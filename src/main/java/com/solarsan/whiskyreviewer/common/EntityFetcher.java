package com.solarsan.whiskyreviewer.common;

import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import com.solarsan.whiskyreviewer.brand.repository.BrandRepositoryManager;
import com.solarsan.whiskyreviewer.review.model.ReviewEntity;
import com.solarsan.whiskyreviewer.review.repository.ReviewRepositoryManager;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import com.solarsan.whiskyreviewer.reviewer.repository.ReviewerRepositoryManager;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
import com.solarsan.whiskyreviewer.whisky.repository.WhiskyRepositoryManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class EntityFetcher {

    private final BrandRepositoryManager brandRepositoryManager;
    private final ReviewRepositoryManager reviewRepositoryManager;
    private final ReviewerRepositoryManager reviewerRepositoryManager;
    private final WhiskyRepositoryManager whiskyRepositoryManager;

    public BrandEntity getBrandEntity(final UUID id) {
        return brandRepositoryManager.getEntity(id);
    }

    public ReviewEntity getReviewEntity(final UUID id) {
        return reviewRepositoryManager.getEntity(id);
    }

    public ReviewerEntity getReviewerEntity(final UUID id) {
        return reviewerRepositoryManager.getEntity(id);
    }

    public WhiskyEntity getWhiskyEntity(final UUID id) {
        return whiskyRepositoryManager.getEntity(id);
    }

}
