package com.solarsan.whiskyreviewer.review.model;

import com.solarsan.whiskyreviewer.review.dto.NewReviewDTO;
import com.solarsan.whiskyreviewer.review.dto.ReviewDTO;
import com.solarsan.whiskyreviewer.reviewer.model.ReviewerEntity;
import com.solarsan.whiskyreviewer.whisky.model.WhiskyEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Review")
@Table(name = "review")
public class ReviewEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "score", nullable = false)
    private float score;

    @Column(name = "text")
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "whisky_id", nullable = false)
    private WhiskyEntity whisky;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private ReviewerEntity reviewer;

    public static ReviewEntity from(final ReviewerEntity reviewer, final WhiskyEntity whisky, final NewReviewDTO dto) {
        return ReviewEntity
                .builder()
                .id(UUID.randomUUID())
                .score(dto.getScore())
                .text(dto.getText())
                .reviewer(reviewer)
                .whisky(whisky)
                .build();
    }

    public static ReviewEntity from(final ReviewDTO dto) {
        return ReviewEntity
                .builder()
                .id(dto.getId())
                .score(dto.getScore())
                .text(dto.getText())
                .build();
    }
}
