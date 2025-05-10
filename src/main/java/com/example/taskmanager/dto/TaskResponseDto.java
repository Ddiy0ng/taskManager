package com.example.taskmanager.dto;

import com.example.taskmanager.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor //조회 시
public class TaskResponseDto {
    private Long taskId;
    private String name;
    private String tasks;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp postDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp updateDate;

    //dto 반환
    public TaskResponseDto(Task requestTask) {
        this.taskId = requestTask.getTaskId();
        this.name = requestTask.getName();
        this.tasks = requestTask.getTasks();
        this.postDate = requestTask.getPostDate();
        this.updateDate = requestTask.getUpdateDate();
    }
}