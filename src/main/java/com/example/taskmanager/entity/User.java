package com.example.taskmanager.entity;

import com.example.taskmanager.dto.TaskRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class User {
    //일정 객체
    private Long userId;
    private String userName;
    private String email;
    private Timestamp postDate;
    private Timestamp updateDate;
}
