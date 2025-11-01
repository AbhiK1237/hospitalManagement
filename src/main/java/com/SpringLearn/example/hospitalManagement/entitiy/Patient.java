package com.SpringLearn.example.hospitalManagement.entitiy;

import com.SpringLearn.example.hospitalManagement.Enum.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_email",columnNames = {"email"}),
               @UniqueConstraint(name = "unique_Patient_name_birthdate" , columnNames = {"name","birthdate"})
        },
        indexes = {
                @Index(name = "birthdate_index", columnList = "birthdate")
        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private String gender;

    private LocalDate birthdate;

    @CreationTimestamp
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType blood_group;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id") // owning side
    private Insurance insurance;

    @OneToMany(mappedBy = "patient",cascade = {CascadeType.REMOVE},orphanRemoval = true,fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();
}
