package com.iman.taskmanager.repository;

import com.iman.taskmanager.entity.Task;
import com.iman.taskmanager.entity.TaskStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    // Find tasks by status
    List<Task> findByStatus(TaskStatus status);

    // Find tasks containing text in title (case-insensitive)
    List<Task> findByTitleContainingIgnoreCase(String title);

    // Count tasks by status
    Long countByStatus(TaskStatus status);

    // Custom query --> finding recent tasks
    @Query("SELECT t FROM Task t WHERE t.createdAt >= :date ORDER BY t.createdAt DESC")
    List<Task> findRecentTasks(@Param("date") LocalDateTime date);
    





    
}