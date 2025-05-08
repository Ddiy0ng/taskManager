package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Service        //@Component와 동일, SpringBean으로 등록한다는 뜻
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public TaskResponseDto saveTask(TaskRequestDto taskRequestDto){
        Task task = new Task(taskRequestDto.getPassword(), taskRequestDto.getName(), taskRequestDto.getTasks());
        return taskRepository.saveTask(task);
    }

    public void deleteTask(Long id) {
        // task 삭제
        int deletedRow = taskRepository.deleteTask(id);
        // 삭제된 row가 0개 라면
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

    }
}
