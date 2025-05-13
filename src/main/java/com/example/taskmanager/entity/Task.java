package com.example.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Task {
    //일정 객체
    private long taskId;
    private String userName;
    private String tasks;
    private Timestamp postDate;
    private Timestamp updateDate;
    //password는 db 저장 시에만 필요하므로 삭제
}
