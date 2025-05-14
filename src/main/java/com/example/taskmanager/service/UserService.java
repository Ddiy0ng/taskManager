package com.example.taskmanager.service;

import com.example.taskmanager.dto.UserRequestDto;
import com.example.taskmanager.dto.UserResponseDto;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    //비즈니스 로직 담당

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 생성
    public void createUser(UserRequestDto userRequestDto){
        userRepository.createUser(userRequestDto);
    }

    // 전체 조회
    public List<UserResponseDto> readAllUsers(){
        List<User> userList = userRepository.readAllUsers();
        List<UserResponseDto> userListDto = userList.stream().map(user -> new UserResponseDto(user)).collect(Collectors.toList());
        return userListDto;
    }

    // 단건 조회
    public UserResponseDto readUser(long userId){
        User resultUser = userRepository.readUser(userId);
        UserResponseDto resultUserDto = new UserResponseDto(resultUser);
        return resultUserDto;
    }

    // 수정
    public void updateUser(long userId, UserRequestDto userRequestDto){
        userRepository.updateUser(userId, userRequestDto);
    }

    // 삭제
    public int deleteUser(long userId){
        return userRepository.deleteUser(userId);
    }
}
