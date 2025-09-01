package com.iman.taskmanager.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

@RestController //tells Spring “this class handles HTTP requests and returns JSON/text”
@RequestMapping("/api/tasks")//every method inside will start with /api/tasks
public class TaskController {
    // Simple in-memory storage for now
    private List<String> tasks = new ArrayList<>();

       // GET all tasks
    @GetMapping
    public ResponseEntity<List<String>> getAllTasks(){
        return ResponseEntity.ok(tasks);
    }

    // GET welcome message
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from Iman's Task Manager API! ");
    }

 
    // POST create new task
    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody String taskTitle) {
        tasks.add(taskTitle);
        return ResponseEntity.ok("Task created: " + taskTitle);
    }
    // GET count of tasks
    @GetMapping("/count")
    public ResponseEntity<String> getTaskCount() {
        return ResponseEntity.ok("Total tasks: " + tasks.size());
    }


}
