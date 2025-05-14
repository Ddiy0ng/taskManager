package com.example.taskmanager.dto;

import com.example.taskmanager.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponseDto {
    //Task 응답용
    private long taskId;
    private String userName;
    private String tasks;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp postDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp updateDate;

    public TaskResponseDto(Task task) {
        this.taskId = task.getTaskId();
        this.userName = task.getUserName();
        this.tasks = task.getTasks();
        this.postDate = task.getPostDate();
        this.updateDate = task.getUpdateDate();
    }
}