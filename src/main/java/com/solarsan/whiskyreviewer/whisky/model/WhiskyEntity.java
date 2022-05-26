package com.solarsan.whiskyreviewer.whisky.model;

import com.solarsan.whiskyreviewer.brand.model.BrandEntity;
import com.solarsan.whiskyreviewer.whisky.dto.NewWhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.dto.WhiskyDTO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
                .name(dto.getName())
                .alcohol(dto.getAlcohol())
                .type(dto.getType())
                .age(dto.getAge())
                .brand(brand)
                .build();
    }

    public static WhiskyEntity from(final WhiskyDTO dto) {
        return WhiskyEntity
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .alcohol(dto.getAlcohol())
                .type(dto.getType())
                .age(dto.getAge())
                .build();
    }
}
