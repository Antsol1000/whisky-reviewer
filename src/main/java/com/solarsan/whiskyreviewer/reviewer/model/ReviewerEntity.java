package com.solarsan.whiskyreviewer.reviewer.model;

import com.solarsan.whiskyreviewer.reviewer.dto.ReviewerDTO;
import com.solarsan.whiskyreviewer.reviewer.dto.NewReviewerDTO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "Reviewer")
@Table(name = "reviewer")
public class ReviewerEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    public static ReviewerEntity from(final NewReviewerDTO dto) {
        return ReviewerEntity.builder().name(dto.getName()).build();
    }

    public static ReviewerEntity from(final ReviewerDTO dto) {
        return ReviewerEntity.builder().id(dto.getId()).name(dto.getName()).build();
    }

}
