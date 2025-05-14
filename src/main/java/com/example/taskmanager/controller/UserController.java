package com.example.taskmanager.controller;

import com.example.taskmanager.dto.UserRequestDto;
import com.example.taskmanager.dto.UserResponseDto;
import com.example.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //데이터를 json형태로 사용
@RequestMapping("/api")   //prefix하는 url 사용 시 설정
public class UserController {
    //요청, 응답 담당

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // 생성
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
        return new ResponseEntity<>("유저 데이터를 생성했습니다.", HttpStatus.CREATED);
    }
    // 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> readAllUsers(){
        List<UserResponseDto> userList = userService.readAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    // 단건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> readUser(@PathVariable long userId){
        UserResponseDto userResponseDto = userService.readUser(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
    // 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable long userId, @Valid@RequestBody UserRequestDto userRequestDto){
        userService.updateUser(userId, userRequestDto);
        return new ResponseEntity<>("유저 데이터를 수정했습니다", HttpStatus.OK);
    }
    // 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        if(userService.deleteUser(userId) == 0)
            return new ResponseEntity<>("해당 ID의 데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("유저 데이터를 삭제했습니다.", HttpStatus.OK);
    }
}
