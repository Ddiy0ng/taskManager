package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController //데이터를 json형태로 사용
@RequestMapping("/api")   //prefix하는 url 사용 시 설정
public class TaskController {
    //요청, 응답 담당

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/tasks") // 요청
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        // ServiceLayer 호출 및 응답
        return new ResponseEntity<>(taskService.saveTask(taskRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{id}") // 요청
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
