package com.filrouge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sneak_peeks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SneakPeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "is_published")
    private boolean isPublished = false;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

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

    public void publish() {
        this.isPublished = true;
        this.publishedAt = LocalDateTime.now();
    }

    public void unpublish() {
        this.isPublished = false;
        this.publishedAt = null;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }
}