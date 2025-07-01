package com.filrouge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User applicant;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "application_status", nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @Column(name = "cover_letter", length = 2000)
    private String coverLetter;

    @Column(name = "cv_url")
    private String cvUrl;

    @Column(name = "additional_documents_url")
    private String additionalDocumentsUrl;

    @Column(name = "expected_salary")
    private String expectedSalary;

    @Column(name = "availability_date")
    private LocalDateTime availabilityDate;

    @Column(name = "application_date", nullable = false)
    private LocalDateTime applicationDate;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    @Column(name = "review_notes", length = 1000)
    private String reviewNotes;

    @Column(name = "interview_date")
    private LocalDateTime interviewDate;

    @Column(name = "interview_location")
    private String interviewLocation;

    @Column(name = "interview_notes", length = 1000)
    private String interviewNotes;

    @PrePersist
    protected void onCreate() {
        applicationDate = LocalDateTime.now();
    }

    public enum ApplicationStatus {
        PENDING, REVIEWING, INTERVIEW_SCHEDULED, INTERVIEWED, ACCEPTED, REJECTED, WITHDRAWN
    }
} 