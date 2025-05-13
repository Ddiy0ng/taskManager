package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //데이터를 json형태로 사용
@RequestMapping("/api")   //prefix하는 url 사용 시 설정
public class TaskController {
    //요청, 응답 담당

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/users/{userId}/tasks") // 요청
    public ResponseEntity<String> createUser(@PathVariable long userId, @Valid @RequestBody TaskRequestDto taskRequestDto) {
        taskService.createTask(userId, taskRequestDto);
        return new ResponseEntity<>("일정을 생성했습니다.", HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/tasks")
    public ResponseEntity<List<TaskResponseDto>> readAllTasks(@PathVariable long userId, @RequestParam(defaultValue = "1") int pageNumber){
        List<TaskResponseDto> taskList = taskService.readAllTasks(userId, pageNumber);
        if(taskList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<TaskResponseDto> readTask(@PathVariable long userId, @PathVariable long taskId, @RequestBody TaskRequestDto taskRequestDto){
        TaskResponseDto taskResponseDto = taskService.readTask(userId, taskId, taskRequestDto);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable long taskId, @RequestBody TaskRequestDto taskRequestDto){
        taskService.updateTask(taskId, taskRequestDto);
        return new ResponseEntity<>("일정을 수정했습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable long taskId, @RequestBody TaskRequestDto taskRequestDto){
        if(taskService.deleteTask(taskId, taskRequestDto) == 0)
            return new ResponseEntity<>("해당 데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("일정을 삭제했습니다.", HttpStatus.OK);
    }
}
