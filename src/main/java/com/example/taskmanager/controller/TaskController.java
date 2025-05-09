package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.service.TaskService;
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


    @PostMapping("/tasks") // 요청
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        //@RequestBody가 클라이언트가 보낸 JSON(비밀번호, 이름, 일정) 받아옴
        //저장
        TaskResponseDto taskResponseDto = taskService.saveTask(taskRequestDto);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDto>> readAllTasks(){
        return new ResponseEntity<>(taskService.readAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponseDto> readTask(@PathVariable Long taskId){
        return new ResponseEntity<>(taskService.readTask(taskId), HttpStatus.OK);
    }






    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
