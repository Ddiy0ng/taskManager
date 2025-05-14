package com.example.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {
    //Task 요청용
    @NotBlank
    private String password;
    @NotBlank
    private String tasks;

}

