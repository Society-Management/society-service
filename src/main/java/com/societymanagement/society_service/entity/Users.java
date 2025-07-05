package com.societymanagement.society_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@ToString
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Filter(name = "societyFilter", condition = "society_id = :societyId")
public class Users extends BaseEntityV1 implements Serializable {

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String fullName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 100, message = "Password must be between 6 to 100 characters")
    private String password;

    @NotBlank(message = "Aadhar number is required")
    @Pattern(regexp = "\\d{12}", message = "Aadhar number must be a 12-digit number")
    private String aadharNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email can't be longer than 100 characters")
    private String emailAddress;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be a 10-digit number")
    private String contactInfo;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Flats> flatOwned;
    //OneToMany -> No Columns added
    //ManyToOne -> JoinColumn added
    @OneToOne
    @JoinColumn(name = "flat_rented", nullable = true)
    @ToString.Exclude
    private Flats flatRented; // Reference to the flat rented by the user, if any

//    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Issue> issueList;
}