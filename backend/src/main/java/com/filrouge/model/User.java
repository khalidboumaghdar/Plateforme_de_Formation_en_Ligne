package com.filrouge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // Relations avec les nouvelles entités
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseProgress> courseProgresses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificate> certificates = new ArrayList<>();

    @OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> contributedDocuments = new ArrayList<>();

    @OneToMany(mappedBy = "validatedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> validatedDocuments = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tutorial> createdTutorials = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CV> cvs = new ArrayList<>();

    @OneToMany(mappedBy = "verifiedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CV> verifiedCVs = new ArrayList<>();

    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOffer> postedJobOffers = new ArrayList<>();

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meeting> hostedMeetings = new ArrayList<>();

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> instructedCourses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEnrollment> courseEnrollments = new ArrayList<>();

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> jobApplications = new ArrayList<>();

    @OneToMany(mappedBy = "reviewedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> reviewedApplications = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}