package com.solarsan.whiskyreviewer.reviewer.repository;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.NewReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.exceptions.ReviewerNotFoundException;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ReviewerRepositoryManager {
    private final ReviewerRepository reviewerRepository;

    public ReviewerEntity save(final ReviewerEntity entity) {
        return reviewerRepository.save(entity);
    }

    public List<ReviewerDTO> getAll() {
        final List<ReviewerDTO> e = new ArrayList<>();
        reviewerRepository.findAll().forEach(x -> e.add(ReviewerDTO.from(x)));
        return e;
    }

    public Optional<ReviewerDTO> get(final UUID id) {
        return reviewerRepository.findById(id).map(ReviewerDTO::from);
    }

    public IdResponseDTO update(final UUID id, final NewReviewerDTO dto) {
        final ReviewerEntity entity =
                reviewerRepository.findById(id).orElseThrow(() -> new ReviewerNotFoundException(id.toString()));
        entity.setName(dto.getName());
        final ReviewerEntity saved = reviewerRepository.save(entity);
        return IdResponseDTO.builder().id(saved.getId()).build();
    }

    public void delete(final UUID id) {
        reviewerRepository.findById(id).orElseThrow(() -> new ReviewerNotFoundException(id.toString()));
        reviewerRepository.deleteById(id);
    }
}
