package com.gamexo.backend.service;

import com.gamexo.backend.config.ResponseExeption;
import com.gamexo.backend.dto.user.UserInfoDTO;
import com.gamexo.backend.mapper.UserMapper;
import com.gamexo.backend.model.UserEntity;
import com.gamexo.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserInfoDTO getSingleUser(Long id){
        return  userRepository.findById(id)
                .filter(user -> !user.getRol().name().equalsIgnoreCase("admin"))
                .map(userMapper::userToDto)
                .orElseThrow(()-> new ResponseExeption("404","ID NOT FOUND"));
    }

    public List<UserInfoDTO> findAll() {
        return userRepository.findAll().stream()
                .filter(user -> !user.getRol().name().equalsIgnoreCase("admin"))
                .map(UserInfoDTO::new).toList();
    }
}
