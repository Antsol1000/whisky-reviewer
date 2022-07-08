package com.solarsan.whiskyreviewer.whisky.model;

import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import com.solarsan.whiskyreviewer.whisky.dto.NewWhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.dto.WhiskyDTO;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Whisky")
@Table(name = "whisky")
public class WhiskyEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "alcohol", nullable = false)
    private int alcohol;

    @Column(name = "type", nullable = false)
    private WhiskyType type;

    @Column(name = "age")
    private int age;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    public static WhiskyEntity from(final BrandEntity brand, final NewWhiskyDTO dto) {
        return WhiskyEntity
                .builder()
                .id(UUID.randomUUID())
                .name(dto.name())
                .alcohol(dto.alcohol())
                .type(dto.type())
                .age(dto.age())
                .brand(brand)
                .build();
    }

    public static WhiskyEntity from(final WhiskyDTO dto) {
        return WhiskyEntity
                .builder()
                .id(dto.id())
                .name(dto.name())
                .alcohol(dto.alcohol())
                .type(dto.type())
                .age(dto.age())
                .build();
    }
}
