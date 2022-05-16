package com.solarsan.whiskyreviewer.review.model;

import com.solarsan.whiskyreviewer.review.dto.NewReviewDTO;
import com.solarsan.whiskyreviewer.review.dto.ReviewDTO;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Review")
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "score", nullable = false)
    private float score;

    @Column(name = "text", nullable = true)
    private String text;

    @Column(name = "whisky_id", nullable = false)
    private UUID whiskyId;

    @Column(name = "reviewer_id", nullable = false)
    private UUID reviewerId;

    public static ReviewEntity from(final NewReviewDTO dto) {
        return ReviewEntity
                .builder()
                .score(dto.getScore())
                .text(dto.getText())
                .whiskyId(dto.getWhiskyId())
                .build();
    }

    public static ReviewEntity from(final ReviewDTO dto) {
        return ReviewEntity
                .builder()
                .id(dto.getId())
                .score(dto.getScore())
                .text(dto.getText())
                .whiskyId(dto.getWhiskyId())
                .build();
    }
}
