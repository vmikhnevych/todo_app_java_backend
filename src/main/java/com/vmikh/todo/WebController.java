package com.vmikh.todo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class WebController {
    private final TaskRepository repository;

    @GetMapping("/todo")
    public String main(Model model) {
        List<Task> tasks = repository.findAll();
        model.addAttribute("tasks", tasks);
        return "main";
    }

    @PostMapping("/todo/create")
    public String createTask(String name) {
        Task task = new Task();
        task.setName(TodoUtil.addCurrentDateToName(name));
        repository.save(task);
        return "redirect:/todo";
    }

}
