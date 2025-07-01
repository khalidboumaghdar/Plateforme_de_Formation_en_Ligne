package com.filrouge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "talent_hub_offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalentHubOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type", nullable = false)
    private OfferType offerType;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_logo_url")
    private String companyLogoUrl;

    @Column(nullable = false)
    private String location;

    @Column(name = "is_remote")
    private boolean isRemote;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "application_deadline")
    private LocalDateTime applicationDeadline;

    @Column(name = "required_skills")
    @ElementCollection
    private List<String> requiredSkills = new ArrayList<>();

    @Column(name = "is_published")
    private boolean isPublished = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by")
    private User postedBy;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TalentHubApplication> applications = new ArrayList<>();

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

    public void addApplication(TalentHubApplication application) {
        applications.add(application);
        application.setOffer(this);
    }

    public void removeApplication(TalentHubApplication application) {
        applications.remove(application);
        application.setOffer(null);
    }

    public enum OfferType {
        INTERNSHIP,
        PFE,
        JOB
    }
}