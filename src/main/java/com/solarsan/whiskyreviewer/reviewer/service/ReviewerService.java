package com.solarsan.whiskyreviewer.reviewer.service;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.NewReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import com.solarsan.whiskyreviewer.reviewer.repository.ReviewerRepositoryManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ReviewerService {

    private final ReviewerRepositoryManager repositoryManager;

    @Transactional
    public IdResponseDTO createReviewer(final NewReviewerDTO dto) {
        final ReviewerEntity entity = ReviewerEntity.from(dto);
        final ReviewerEntity saved = repositoryManager.save(entity);
        log.info("Created new reviewer {} with id {}", saved.getName(), saved.getId());
        return IdResponseDTO.builder().id(saved.getId()).build();
    }

    public List<ReviewerDTO> getReviewers() {
        return repositoryManager.getAll();
    }

    public Optional<ReviewerDTO> getReviewer(final UUID id) {
        return repositoryManager.get(id);
    }

    @Transactional
    public IdResponseDTO updateReviewerById(final UUID id, final NewReviewerDTO reviewerDTO) {
        final IdResponseDTO response = repositoryManager.update(id, reviewerDTO);
        log.info("Updated reviewer with id {}", response.getId());
        return response;
    }

    @Transactional
    public void deleteReviewer(final UUID id) {
        repositoryManager.delete(id);
        log.info("Deleted reviewer with id {}", id);
    }

}
