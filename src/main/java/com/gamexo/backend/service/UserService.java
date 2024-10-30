package com.gamexo.backend.service;

import com.gamexo.backend.dto.user.UserInfoDTO;
import com.gamexo.backend.mapper.UserMapper;
import com.gamexo.backend.model.UserEntity;
import com.gamexo.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

        Optional<UserEntity> optUser =  userRepository.findById(id);

        if(optUser.isEmpty()) throw new EntityNotFoundException("Entity not found");

        return userMapper.userToDto(optUser.get());

    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
