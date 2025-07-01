package com.filrouge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "meetings")
public class Meeting {

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
    @Column(name = "platform", nullable = false)
    private Platform platform;

    @Column(name = "meeting_url", nullable = false)
    private String meetingUrl;

    @Column(name = "meeting_id")
    private String meetingId;

    @Column(name = "meeting_password")
    private String meetingPassword;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "max_participants")
    private Integer maxParticipants;

    @Column(name = "current_participants")
    private Integer currentParticipants = 0;

    @Column(name = "is_recording_enabled")
    private boolean recordingEnabled = false;

    @Column(name = "recording_url")
    private String recordingUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    private User host;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "is_recurring")
    private boolean recurring = false;

    @Column(name = "recurrence_pattern")
    private String recurrencePattern;

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

    public enum Platform {
        ZOOM, TEAMS, GOOGLE_MEET, OTHER
    }
} 