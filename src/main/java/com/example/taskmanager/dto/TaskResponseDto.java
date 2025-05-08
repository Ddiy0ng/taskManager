package com.example.taskmanager.dto;

import com.example.taskmanager.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TaskResponseDto {
    private Long id;
    private String name;
    private String tasks;
    private Timestamp postDate;


    public TaskResponseDto(long id, String name, String tasks, Timestamp postDate) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
        this.postDate = postDate;
    }
}