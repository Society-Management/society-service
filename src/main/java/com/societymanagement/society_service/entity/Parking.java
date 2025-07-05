package com.societymanagement.society_service.entity;

import com.societymanagement.society_service.enums.ParkingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Entity
@Table(name = "parking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE parking SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Filter(name = "societyFilter", condition = "society_id = :societyId")
public class Parking extends BaseEntityV1 implements Serializable {

    @NotBlank(message = "Parking number is required")
    @Size(max = 50, message = "Parking number can be at most 50 characters")
    private String parkingNumber;

    @NotBlank(message = "Parking type is required")
    @Enumerated(EnumType.STRING)
    private ParkingType parkingType;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    @ToString.Exclude // avoid circular reference in toString
    private Flats flat;

    @NotNull(message = "isActive flag must not be null")
    private Boolean isActive = true;
}
