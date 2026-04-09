package com.mar1a_ed.e_marketplace.controller;

import com.mar1a_ed.e_marketplace.model.entity.User;
import com.mar1a_ed.e_marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        userService.create(user);

        return ResponseEntity.ok(user);
    }

}
