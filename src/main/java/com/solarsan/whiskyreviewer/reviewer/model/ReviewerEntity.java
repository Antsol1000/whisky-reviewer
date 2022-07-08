package com.solarsan.whiskyreviewer.reviewer.model;

import com.solarsan.whiskyreviewer.reviewer.dto.NewReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "Reviewer")
@Table(name = "reviewer")
public class ReviewerEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    public static ReviewerEntity from(final NewReviewerDTO dto) {
        return ReviewerEntity.builder().id(UUID.randomUUID()).name(dto.name()).build();
    }

    public static ReviewerEntity from(final ReviewerDTO dto) {
        return ReviewerEntity.builder().id(dto.id()).name(dto.name()).build();
    }

}
