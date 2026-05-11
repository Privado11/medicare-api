package com.medicare.api.entities;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    @NotBlank
    @Column(name = "medication_name", nullable = false, length = 150)
    private String medicationName;

    @NotBlank
    @Column(nullable = false, length = 80)
    private String dosage;

    @NotBlank
    @Column(nullable = false, length = 80)
    private String frequency;

    @Column(length = 80)
    private String duration;

    @Column(length = 40)
    private String route;

    @Column(columnDefinition = "TEXT")
    private String instructions;
}
