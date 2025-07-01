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
@Table(name = "documents")
public class Document {

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
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "file_size_bytes")
    private Long fileSizeBytes;

    @Column(name = "file_extension")
    private String fileExtension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor_id")
    private User contributor;

    @Column(name = "is_validated")
    private boolean validated = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validated_by")
    private User validatedBy;

    @Column(name = "validation_date")
    private LocalDateTime validationDate;

    @Column(name = "download_count")
    private Integer downloadCount = 0;

    @Column(name = "is_public")
    private boolean isPublic = true;

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

    public enum DocumentType {
        PDF, WORD, EXCEL, POWERPOINT, IMAGE, VIDEO, AUDIO, OTHER
    }
} 