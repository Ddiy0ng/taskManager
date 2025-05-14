package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.Paging;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.errorHandler.ErrorHandler;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    //비즈니스 로직 담당

    private Paging paging;
    private final TaskRepository taskRepository;
    private final ErrorHandler errorHandler;
    private final JdbcTemplate jdbcTemplate;
    public TaskService(TaskRepository taskRepository, ErrorHandler errorHandler, JdbcTemplate jdbcTemplate){
        this.taskRepository = taskRepository;
        this.errorHandler = errorHandler;
        this.jdbcTemplate = jdbcTemplate;
    }

    // 생성
    public void createTask(long userId, TaskRequestDto taskRequestDto){
        taskRepository.createTask(userId, taskRequestDto);
    }
    // 전체 조회: 페이지를 기준으로 조회
    public List<TaskResponseDto> readAllTasks(long userId, int pageNumber){
        List<Task> taskList = taskRepository.readAllTasks(userId);
        paging = new Paging(pageNumber, taskList);
        List<Task> pageTaskList = paging.slicePageData();
        List<TaskResponseDto> responseTaskList = pageTaskList.stream().map(task -> new TaskResponseDto(task)).collect(Collectors.toList());
        return responseTaskList;
    }
    // 단건 조회
    public TaskResponseDto readTask(long userId, long taskId, TaskRequestDto taskRequestDto){
        errorHandler.passwordCheck(taskRequestDto, jdbcTemplate, taskId);
        Task task = taskRepository.readTask(userId, taskId);
        TaskResponseDto responseTask = new TaskResponseDto(task);
        return responseTask;
    }
    // 수정
    public void updateTask(long taskId, TaskRequestDto taskRequestDto){
        errorHandler.passwordCheck(taskRequestDto, jdbcTemplate, taskId);
        taskRepository.updateTask(taskId, taskRequestDto);
    }
    // 삭제
    public int deleteTask(long taskId, TaskRequestDto taskRequestDto){
        errorHandler.passwordCheck(taskRequestDto, jdbcTemplate, taskId);
        int deleteCheck = taskRepository.deleteTask(taskId);
        return deleteCheck;
    }

}
