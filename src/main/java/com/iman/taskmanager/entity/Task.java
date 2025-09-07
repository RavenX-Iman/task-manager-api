package com.iman.taskmanager.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import com.iman.taskmanager.entity.TaskStatus;


@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Task title is required")
    @Size(max=100, message="Title must be less than 100 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 500, message = "Description must be less than 500 characters")
    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //constructor
    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

        // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


        // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    // no setCreatedAt() — createdAt is set automatically on persist

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    // no setUpdatedAt() — updatedAt is managed automatically by lifecycle callbacks

        @Override
    public String toString() {
        return "Task{id="+id+", title='"+title+"', status="+status+"}";
    }
}


