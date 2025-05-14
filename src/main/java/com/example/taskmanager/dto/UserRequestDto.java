package com.example.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    //User 요청용
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String email;

}

