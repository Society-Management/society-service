package com.societymanagement.society_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "flats")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE flats SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Filter(name = "societyFilter", condition = "society_id = :societyId")
public class Flats extends BaseEntityV1 implements Serializable {

    @NotBlank(message = "Flat address is mandatory")
    @Size(max = 100, message = "Flat address cannot exceed 100 characters")
    private String flatAddress;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner; // optional, so no @NotNull

    @OneToOne
    @JoinColumn(name = "tenant_id")
    private Users tenant; // optional, so no @NotNull

    @NotBlank(message = "Flat type is required")
    private String flatType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "block_id", nullable = false)
    @NotNull(message = "Block must not be null")
    private Blocks block;

    @NotNull(message = "isActive flag must not be null")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Parking> parkingList;
}
