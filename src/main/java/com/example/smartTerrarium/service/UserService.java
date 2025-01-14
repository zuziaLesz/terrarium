package com.example.smartTerrarium.service;

import com.example.smartTerrarium.entity.User;
import com.example.smartTerrarium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createDefaultUser(){
        User user = User.builder()
                .id(1)
                .name("zuzia")
                .email("zuzia")
                .password("zuzia")
                .build();
        return userRepository.save(user);
    }
}
