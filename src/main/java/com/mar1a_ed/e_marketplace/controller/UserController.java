package com.mar1a_ed.e_marketplace.controller;

import com.mar1a_ed.e_marketplace.dto.mapper.UserMapper;
import com.mar1a_ed.e_marketplace.dto.users.UserCreateDto;
import com.mar1a_ed.e_marketplace.dto.users.UserPasswordDto;
import com.mar1a_ed.e_marketplace.dto.users.UserResponseDto;
import com.mar1a_ed.e_marketplace.model.entity.User;
import com.mar1a_ed.e_marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.el.lang.ELArithmetic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto userDto){
        User user = userService.create(UserMapper.toUser(userDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

    @PatchMapping("/update_password/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordDto userPasswordDto){
        userService.updatePassword(id, userPasswordDto.getCurrentPassword(), userPasswordDto.getNewPassword(), userPasswordDto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }
}
