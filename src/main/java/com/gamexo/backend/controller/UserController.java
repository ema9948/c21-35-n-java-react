package com.gamexo.backend.controller;

import com.gamexo.backend.dto.user.UserInfoDTO;
import com.gamexo.backend.model.UserEntity;
import com.gamexo.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

   private final UserService userService;

   @Autowired
   public UserController(UserService userService) {
       this.userService = userService;
   }

    @GetMapping
    public List<UserEntity> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> getUser(@PathVariable Long id) {
        UserInfoDTO userDTO = userService.getSingleUser(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/test") String openEndpoint(){
        return "Hola!!";
    }

}
