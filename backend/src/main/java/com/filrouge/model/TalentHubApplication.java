package com.filrouge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "talent_hub_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalentHubApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private TalentHubOffer offer;

    @Column(name = "cv_url", nullable = false)
    private String cvUrl;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "portfolio_url")
    private String portfolioUrl;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @Column(name = "review_notes")
    private String reviewNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void review(User reviewer, ApplicationStatus newStatus, String notes) {
        this.status = newStatus;
        this.reviewNotes = notes;
        this.reviewedBy = reviewer;
        this.reviewDate = LocalDateTime.now();
    }

    public enum ApplicationStatus {
        PENDING,
        UNDER_REVIEW,
        SHORTLISTED,
        INTERVIEWED,
        ACCEPTED,
        REJECTED
    }
}