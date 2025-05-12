package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.dto.UserResponseDto;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.errorHandler.ErrorHandler;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Service        //@Component와 동일, SpringBean으로 등록한다는 뜻
public class TaskService {

    private final TaskRepository taskRepository;
    private final ErrorHandler errorHandler;
    private final JdbcTemplate jdbcTemplate;
    public TaskService(TaskRepository taskRepository, ErrorHandler errorHandler, JdbcTemplate jdbcTemplate){
        this.taskRepository = taskRepository;
        this.errorHandler = errorHandler;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTask(long userId, TaskRequestDto taskRequestDto){
        taskRepository.createTask(userId, taskRequestDto);
    }

    public List<TaskResponseDto> readAllTasks(long userId){
        List<Task> taskList = taskRepository.readAllTasks(userId);
        List<TaskResponseDto> responseTaskList = taskList.stream().map(task -> new TaskResponseDto(task)).collect(Collectors.toList());
        return responseTaskList;
    }

    public TaskResponseDto readTask(long userId, long taskId, TaskRequestDto taskRequestDto){
        errorHandler.passwordCheck(taskRequestDto, jdbcTemplate, taskId);
        Task task = taskRepository.readTask(userId, taskId);
        TaskResponseDto responseTask = new TaskResponseDto(task);
        return responseTask;
    }

    public void updateTask(long taskId, TaskRequestDto taskRequestDto){
        errorHandler.passwordCheck(taskRequestDto, jdbcTemplate, taskId);
        taskRepository.updateTask(taskId, taskRequestDto);
    }

    public int deleteTask(long taskId, TaskRequestDto taskRequestDto){
        errorHandler.passwordCheck(taskRequestDto, jdbcTemplate, taskId);
        int deleteCheck = taskRepository.deleteTask(taskId);
        return deleteCheck;
    }

}
