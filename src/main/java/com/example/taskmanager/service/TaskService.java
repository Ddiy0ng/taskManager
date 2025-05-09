package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service        //@Component와 동일, SpringBean으로 등록한다는 뜻
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public TaskResponseDto saveTask(TaskRequestDto taskRequestDto){
        Task requestTask = new Task(taskRequestDto);
        Task savedTask = taskRepository.saveTask(requestTask);
        TaskResponseDto responseTask = new TaskResponseDto(savedTask);
        return responseTask;
    }

    public List<TaskResponseDto> readAllTasks() {
        List<Task> listTask = taskRepository.readAllTasks();
        if(listTask.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist");
        return taskRepository.readAllTasks().stream().map(t -> new TaskResponseDto(t.getTaskId(), t.getName(), t.getTasks(), t.getPostDate())).collect(Collectors.toList());
    }

    public TaskResponseDto readTask(Long taskId){
        Task requestTask = taskRepository.readTask(taskId);

        if(requestTask == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + taskId);
        }

        TaskResponseDto responseTask = new TaskResponseDto(requestTask);
        return responseTask;
    }



    public void deleteTask(Long taskId) {
        // task 삭제
        int deletedRow = taskRepository.deleteTask(taskId);
        // 삭제된 row가 0개 라면
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist taskId = " + taskId);
        }

    }

}
