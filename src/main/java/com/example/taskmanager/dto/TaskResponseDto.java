package com.example.taskmanager.dto;

import com.example.taskmanager.entity.Task;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponseDto {
    private Long taskId;
    private String name;
    private String tasks;
    private Timestamp postDate;

    public TaskResponseDto(Task requestTask) {
        this.taskId = requestTask.getTaskId();
        this.name = requestTask.getName();
        this.tasks = requestTask.getTasks();
        this.postDate = requestTask.getPostDate();

    }
}