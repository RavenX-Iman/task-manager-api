package com.iman.taskmanager.controller;


import com.iman.taskmanager.entity.Task;
import com.iman.taskmanager.entity.TaskStatus;
import com.iman.taskmanager.repository.TaskRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@RestController //tells Spring “this class handles HTTP requests and returns JSON/text”
@RequestMapping("/api/tasks")//every method inside will start with /api/tasks
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;//injects repository to call DB methods


       // GET all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    // GET welcome message
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from Iman's Task Manager API! ");
    }

 
    // POST create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.status(201).body(savedTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //put update existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@Valid @RequestBody Task taskDetails){
        Optional<Task> optionalTask = taskRepository.findById(id);

         if (optionalTask.isPresent()) {

            Task task = optionalTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());

            Task updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Delete task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }else{
            return ResponseEntity.notFound().build();
        }

    }


    // GET count of tasks
    @GetMapping("/count")
    public ResponseEntity<String> getTaskCount() {
        long count = taskRepository.count();
        return ResponseEntity.ok("Total tasks: " + count);
    }




}
