package com.vmikh.todo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    //add fields for id, name and completion flag. Annotate class with lombok annotations
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean completed = false;
}
