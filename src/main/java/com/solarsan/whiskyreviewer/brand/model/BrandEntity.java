package com.solarsan.whiskyreviewer.brand.model;

import com.solarsan.whiskyreviewer.brand.dto.BrandDTO;
import com.solarsan.whiskyreviewer.brand.dto.NewBrandDTO;
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
@Entity(name = "Brand")
@Table(name = "brand")
public class BrandEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    public static BrandEntity from(final NewBrandDTO dto) {
        return BrandEntity.builder().id(UUID.randomUUID()).name(dto.getName()).country(dto.getCountry()).build();
    }

    public static BrandEntity from(final BrandDTO dto) {
        return BrandEntity.builder().id(dto.getId()).name(dto.getName()).country(dto.getCountry()).build();
    }

}
