package com.societymanagement.society_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "blocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE blocks SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Filter(name = "societyFilter", condition = "society_id = :societyId")
public class Blocks extends BaseEntityV1 implements Serializable {

    private String blockName;
    private String blockContactNumber;
    private String blockEmail;
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Flats> flats;
    @ManyToOne
    @JoinColumn(name = "society_details", nullable = false)
    @JsonIgnore
    private Society society;

    @Override
    public String toString() {
        return "Blocks{" +
                "blockName='" + blockName + '\'' +
                ", blockContactNumber='" + blockContactNumber + '\'' +
                ", blockEmail='" + blockEmail + '\'' +
                ", flats=" + flats +
                ", society=" + society +
                '}';
    }
}
