package com.example.taskmanager.entity;

import com.example.taskmanager.dto.TaskRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Task {
    //일정 객체
    private Long taskId;
    private String password;
    private String name;
    private String tasks;
    private Timestamp postDate;
    private Timestamp updateDate;

    public Task(long taskId, String name, String tasks, Timestamp postDate, Timestamp updateDate) {
        this.taskId = taskId;
        this.name = name;
        this.tasks = tasks;
        this.postDate = postDate;
        this.updateDate = updateDate;
    }

    //서비스의 저장용
    public Task(TaskRequestDto taskRequestDto) {
        this.password = taskRequestDto.getPassword();
        this.name = taskRequestDto.getName();
        this.tasks = taskRequestDto.getTasks();
    }


}
