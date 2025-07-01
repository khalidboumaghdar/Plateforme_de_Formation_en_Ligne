package com.filrouge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "course_progress")
public class CourseProgress {

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

    @PositiveOrZero
    @Column(name = "progress_percentage", nullable = false)
    private Integer progressPercentage = 0;

    @Column(name = "is_completed")
    private boolean completed = false;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt;

    @PrePersist
    protected void onCreate() {
        startedAt = LocalDateTime.now();
        lastAccessedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastAccessedAt = LocalDateTime.now();
        if (completed && completedAt == null) {
            completedAt = LocalDateTime.now();
        }
    }
} 