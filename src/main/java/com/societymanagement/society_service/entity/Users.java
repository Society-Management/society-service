package com.societymanagement.society_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Filter(name = "societyFilter", condition = "society_id = :societyId")
public class Users extends BaseEntityV1 implements Serializable {

    private String fullName;
    private String password;
    private String aadharNumber;
    private String emailAddress;
    private String contactInfo;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Flats> flatOwned;
    //OneToMany -> No Columns added
    //ManyToOne -> JoinColumn added
    @OneToOne
    @JoinColumn(name = "flat_rented", nullable = true)
    private Flats flatRented; // Reference to the flat rented by the user, if any

//    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Issue> issueList;
}