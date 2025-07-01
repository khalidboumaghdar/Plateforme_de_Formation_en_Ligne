package com.filrouge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "job_offers")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type", nullable = false)
    private OfferType offerType;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "salary_min")
    private BigDecimal salaryMin;

    @Column(name = "salary_max")
    private BigDecimal salaryMax;

    @Column(name = "salary_currency")
    private String salaryCurrency = "EUR";

    @Column(name = "requirements", length = 2000)
    private String requirements;

    @Column(name = "benefits", length = 1000)
    private String benefits;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "application_deadline")
    private LocalDateTime applicationDeadline;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "is_featured")
    private boolean featured = false;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "application_count")
    private Integer applicationCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by")
    private User postedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> applications = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum OfferType {
        INTERNSHIP, PFE, FULL_TIME, PART_TIME, CONTRACT, FREELANCE
    }
} 