package com.solarsan.whiskyreviewer.whisky.model;

import com.solarsan.whiskyreviewer.whisky.dto.NewWhiskyDTO;
import com.solarsan.whiskyreviewer.whisky.dto.WhiskyDTO;
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

    @Column(name = "brand_id")
    private UUID brand_id;

    public static WhiskyEntity from(final NewWhiskyDTO dto) {
        return WhiskyEntity
                .builder()
                .name(dto.getName())
                .alcohol(dto.getAlcohol())
                .type(dto.getType())
                .age(dto.getAge())
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final WhiskyEntity that = (WhiskyEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
