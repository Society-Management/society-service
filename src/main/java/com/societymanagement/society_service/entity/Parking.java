package com.societymanagement.society_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@SQLDelete(sql = "UPDATE flats SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Filter(name = "societyFilter", condition = "society_id = :societyId")
public class Parking extends BaseEntityV1 implements Serializable {

    private String parkingNumber;
    private String parkingType; // e.g., Basement, Open
    @ManyToOne
    @JoinColumn(name = "flat_id", nullable = true)
    private Flats flat; // Reference to the flat associated with this parking spot
    private Boolean isActive = true;

    @Override
    public String toString() {
        return "Parking{" +
                "parkingNumber='" + parkingNumber + '\'' +
                ", parkingType='" + parkingType + '\'' +
                ", flat=" + flat +
                ", isActive=" + isActive +
                '}';
    }
}
