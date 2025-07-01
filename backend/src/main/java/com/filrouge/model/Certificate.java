package com.filrouge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "certificates")
public class Certificate {

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

    @Column(name = "certificate_number", unique = true)
    private String certificateNumber;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "is_valid")
    private boolean valid = true;

    @Column(name = "grade_percentage")
    private Integer gradePercentage;

    @PrePersist
    protected void onCreate() {
        issuedAt = LocalDateTime.now();
        if (certificateNumber == null) {
            certificateNumber = generateCertificateNumber();
        }
    }

    private String generateCertificateNumber() {
        return "CERT-" + System.currentTimeMillis() + "-" + user.getId() + "-" + course.getId();
    }
}