package com.societymanagement.society_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Block name is mandatory")
    @Size(min = 2, max = 50, message = "Block name must be between 2 and 50 characters")
    private String blockName;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String blockContactNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email can't be longer than 100 characters")
    private String blockEmail;

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Flats> flats;

    @ManyToOne(optional = false)
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
                ", society=" + (society != null ? society.getId() : null) +
                '}';
    }
}
