package com.pennywise.pw.controller;

import com.pennywise.pw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/login")
    public ResponseEntity<Boolean> login(@RequestParam String username, @RequestParam String password) {
        if (userService.login(username, password)) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }
    @PostMapping("/users/signup")
    public ResponseEntity<String> signup(@RequestParam String username, @RequestParam String password) {
        if (userService.createUser(username, password)) {
            return ResponseEntity.status(HttpStatus.OK).body("Account Created Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username exists");
        }
    }
}
