package com.filrouge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "material_type", nullable = false)
    private MaterialType materialType;

    @Column(name = "content_url")
    private String contentUrl;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "is_downloadable")
    private boolean isDownloadable;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private CourseSection section;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum MaterialType {
        VIDEO,
        DOCUMENT,
        QUIZ,
        ASSIGNMENT,
        LINK
    }
}