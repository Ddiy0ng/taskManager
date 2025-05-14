package com.example.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class User {
    //사용지 객체
    private long userId;
    private String userName;
    private String email;
    private Timestamp postDate;
    private Timestamp updateDate;
}