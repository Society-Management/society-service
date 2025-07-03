package com.societymanagement.society_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "flats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE flats SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Filter(name = "societyFilter", condition = "society_id = :societyId")
public class Flats extends BaseEntityV1 implements Serializable {

    private String flatAddress;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = true)
    private Users owner;
    @OneToOne
    @JoinColumn(name = "tenant_id", nullable = true)
    private Users tenant;
    private String flatType; // e.g., 1BHK, 2BHK, etc.
    @ManyToOne
    @JoinColumn(name = "block_id", nullable = false)
    private Blocks block;
    private Boolean isActive = true;
    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Parking> parkingList;

    @Override
    public String toString() {
        return "Flats{" +
                "flatAddress='" + flatAddress + '\'' +
                ", owner=" + owner +
                ", tenant=" + tenant +
                ", block=" + block +
                ", isActive=" + isActive +
                ", parkingList=" + parkingList +
                '}';
    }
}
