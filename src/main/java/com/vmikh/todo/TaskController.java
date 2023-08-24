package com.vmikh.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    @GetMapping
    public List<Task> getTasks() {
        return repository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody String name) {
        Task task = new Task();
        task.setName(Util.addCurrentDateToName(name));
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
