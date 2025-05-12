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
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //create
    public void createUser(UserRequestDto userRequestDto){
        userRepository.createUser(userRequestDto);
    }

    //readAll
    public List<UserResponseDto> readAllUsers(){
        List<User> userList = userRepository.readAllUsers();
        List<UserResponseDto> userListDto = userList.stream().map(user -> new UserResponseDto(user)).collect(Collectors.toList());
        return userListDto;
    }

    //read
    public UserResponseDto readUser(Long userId){
        User resultUser = userRepository.readUser(userId);
        UserResponseDto resultUserDto = new UserResponseDto(resultUser);
        return resultUserDto;
    }

    //update
    public void updateUser(Long userId, UserRequestDto userRequestDto){
        userRepository.updateUser(userId, userRequestDto);
    }

    //delete
    public int deleteUser(Long userId){
        return userRepository.deleteUser(userId);
    }
}
