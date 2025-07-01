package com.filrouge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "course_enrollments")
public class CourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "enrollment_status", nullable = false)
    private EnrollmentStatus status = EnrollmentStatus.PENDING;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDateTime enrollmentDate;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "certificate_issued")
    private boolean certificateIssued = false;

    @Column(name = "notes", length = 1000)
    private String notes;

    @PrePersist
    protected void onCreate() {
        enrollmentDate = LocalDateTime.now();
    }

    public enum EnrollmentStatus {
        PENDING, ACTIVE, COMPLETED, CANCELLED, EXPIRED
    }

    public enum PaymentStatus {
        PENDING, PAID, FAILED, REFUNDED, CANCELLED
    }
}