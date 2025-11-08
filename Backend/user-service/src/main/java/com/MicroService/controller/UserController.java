package com.MicroService.controller;

import com.MicroService.modal.User;
import com.MicroService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("api/users")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public User getUser(){
        User user = new User();
        user.setEmail("mohit@gmail.com");
        user.setFullName("mohit kumar");
        user.setPhone("8778150648");
        user.setRole("Customer");

        return user;

    }
}
