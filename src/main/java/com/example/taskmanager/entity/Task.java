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
public class Task {
    //일정 객체
    private Long id;
    private String password;
    private String name;
    private String tasks;
    private Timestamp postDate;

    public Task(String password, String name, String tasks){
        this.password = password;
        this.tasks = name;
        this.name = tasks;
        postDate = new Timestamp(System.currentTimeMillis());
    }

    public void update(String password, String name, String tasks) {
        this.password = password;
        this.tasks = name;
        this.name = tasks;
        postDate = new Timestamp(System.currentTimeMillis());
    }

}
