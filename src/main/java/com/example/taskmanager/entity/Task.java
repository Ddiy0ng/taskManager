package com.example.taskmanager.entity;

import com.example.taskmanager.dto.TaskRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Task {
    //일정 객체
    private Long taskId;
    private String password;
    private String userId;
    private String userName;
    private String tasks;
    private Timestamp postDate;
    private Timestamp updateDate;

    public Task(long taskId, String userName, String tasks, Timestamp postDate, Timestamp updateDate){
        this.taskId = taskId;
        this.userName = userName;
        this.tasks = tasks;
        this.postDate = postDate;
        this.updateDate = updateDate;

    }

}
