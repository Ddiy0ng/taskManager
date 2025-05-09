package com.example.taskmanager.entity;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Task {
    //일정 객체
    private Long taskId;
    private String password;
    private String name;
    private String tasks;
    private Timestamp postDate;


    public Task(long taskId, String name, String tasks, Timestamp postDate) {
        this.taskId = taskId;
        this.name = name;
        this.tasks = tasks;
        this.postDate = postDate;
    }

    //서비스의 저장용
    public Task(TaskRequestDto taskRequestDto) {
        this.password = taskRequestDto.getPassword();
        this.name = taskRequestDto.getName();
        this.tasks = taskRequestDto.getTasks();
        postDate = new Timestamp(System.currentTimeMillis());
    }

    public void update(String password, String name, String tasks) {
        this.name = name;
        this.tasks = tasks;
        this.postDate = new Timestamp(System.currentTimeMillis());
    }

}
