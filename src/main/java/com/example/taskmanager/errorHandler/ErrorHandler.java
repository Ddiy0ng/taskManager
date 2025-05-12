package com.example.taskmanager.errorHandler;

import com.example.taskmanager.dto.TaskRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorHandler {

    public void passwordCheck(TaskRequestDto taskRequestDto, JdbcTemplate jdbcTemplate, long taskId){
        String savedPassword = jdbcTemplate.queryForObject("select password from schedule where taskId = ?", String.class, taskId);
        String inputPassword = taskRequestDto.getPassword();
        if(!inputPassword.equals(savedPassword)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Does not match password =" + taskId);
        }
    }



}
