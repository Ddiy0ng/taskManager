package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.Task;
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
    private final JdbcTemplate jdbcTemplate;

    public TaskService(TaskRepository taskRepository, DataSource dataSource){
        this.taskRepository = taskRepository;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public TaskResponseDto saveTask(TaskRequestDto taskRequestDto){
        Task requestTask = new Task(taskRequestDto);
        Task savedTask = taskRepository.saveTask(requestTask);
        TaskResponseDto responseTask = new TaskResponseDto(savedTask);
        return responseTask;
    }

    public List<TaskResponseDto> readAllTasks() {
        List<Task> taskList = taskRepository.readAllTasks();
        if(taskList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist");
        //비밀번호 제외
        return taskRepository.readAllTasks().stream().map(task -> new TaskResponseDto(task)).collect(Collectors.toList());
    }

    public TaskResponseDto readTask(Long taskId, TaskRequestDto taskRequestDto){
        String savedPassword = jdbcTemplate.queryForObject("select password from schedule where taskId = ?", String.class, taskId);
        String inputPassword = taskRequestDto.getPassword();
        if(!inputPassword.equals(savedPassword)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Does not match password =" + taskId);
        }

        Task requestTask = taskRepository.readTask(taskId);
        TaskResponseDto responseTask = new TaskResponseDto(requestTask);
        if(requestTask == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + taskId);
        }

        return responseTask;
    }

    //수정
    public TaskResponseDto updateTask(Long taskId, TaskRequestDto taskRequestDto) {
        String savedPassword = jdbcTemplate.queryForObject("select password from schedule where taskId = ?", String.class, taskId);
        String inputPassword = taskRequestDto.getPassword();
        if(!inputPassword.equals(savedPassword)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Does not match password =" + taskId);
        }

        if(taskRequestDto.getName() == null || taskRequestDto.getTasks() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name and tasks are required values.");

        int updatedRow = taskRepository.updateTask(taskId, taskRequestDto);
        if(updatedRow == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");

        Task requestTask = taskRepository.readTask(taskId);
        TaskResponseDto responseTask = new TaskResponseDto(requestTask);

        return responseTask;
    }

    public void deleteTask(Long taskId, TaskRequestDto taskRequestDto) {
        String savedPassword = jdbcTemplate.queryForObject("select password from schedule where taskId = ?", String.class, taskId);
        String inputPassword = taskRequestDto.getPassword();
        if(!inputPassword.equals(savedPassword)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Does not match password =" + taskId);
        }

        // task 삭제
        int deletedRow = taskRepository.deleteTask(taskId);
        // 삭제된 row가 0개 라면
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist taskId = " + taskId);
        }

    }



}
