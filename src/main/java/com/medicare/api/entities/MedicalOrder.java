package com.medicare.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.medicare.api.enums.MedicalOrderStatus;
import com.medicare.api.enums.MedicalOrderType;
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
public class MedicalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_entry_id", nullable = false)
    private RecordEntry recordEntry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MedicalOrderType type;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private MedicalOrderStatus status = MedicalOrderStatus.PENDING;

    @Column(updatable = false)
    private LocalDateTime issuedAt;

    private LocalDate validUntil;

    @PrePersist
    protected void onCreate() {
        issuedAt = LocalDateTime.now();
    }
}
