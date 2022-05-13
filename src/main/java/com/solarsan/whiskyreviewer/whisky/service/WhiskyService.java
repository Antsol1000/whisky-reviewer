package com.solarsan.whiskyreviewer.whisky.service;

import com.solarsan.whiskyreviewer.common.IdResponseDTO;
import com.solarsan.whiskyreviewer.whisky.dto.WhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.dto.NewWhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
import com.solarsan.whiskyreviewer.whisky.repository.WhiskyRepositoryManager;
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
public class WhiskyService {

    private final WhiskyRepositoryManager repositoryManager;

    @Transactional
    public IdResponseDTO createWhisky(final NewWhiskyDTO dto) {
        final WhiskyEntity entity = WhiskyEntity.from(dto);
        final WhiskyEntity saved = repositoryManager.save(entity);
        log.info("Created new whisky {} with id {}", saved.getName(), saved.getId());
        return IdResponseDTO.builder().id(saved.getId()).build();
    }

    public List<WhiskyDTO> getWhiskies() {
        return repositoryManager.getAll();
    }

    public Optional<WhiskyDTO> getWhisky(final UUID id) {
        return repositoryManager.get(id);
    }

    @Transactional
    public IdResponseDTO updateWhiskyById(final UUID id, final NewWhiskyDTO whiskyDTO) {
        final IdResponseDTO response = repositoryManager.update(id, whiskyDTO);
        log.info("Updated whisky with id {}", response.getId());
        return response;
    }

    @Transactional
    public void deleteWhisky(final UUID id) {
        repositoryManager.delete(id);
        log.info("Deleted whisky with id {}", id);
    }

}
