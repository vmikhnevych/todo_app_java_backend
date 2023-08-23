package com.vmikh.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    @GetMapping
    public List<Task> getTasks() {
        return repository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    @DeleteMapping
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/complete")
    public Task markComplete(Long id) {
        Task task = repository.findById(id).orElseThrow();
        task.setCompleted(true);
        return repository.save(task);
    }

}
