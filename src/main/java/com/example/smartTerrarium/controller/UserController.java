package com.example.smartTerrarium.controller;

import com.example.smartTerrarium.entity.User;
import com.example.smartTerrarium.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createDefaultUser")
    public ResponseEntity<User> createDefaultUser () {
        User user = userService.createDefaultUser();
        return ResponseEntity.ok(user);
    }
}
