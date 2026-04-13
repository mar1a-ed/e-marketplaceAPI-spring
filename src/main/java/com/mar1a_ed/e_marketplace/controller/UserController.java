package com.mar1a_ed.e_marketplace.controller;

import com.mar1a_ed.e_marketplace.dto.user.UserCreateDto;
import com.mar1a_ed.e_marketplace.dto.user.UserMapper;
import com.mar1a_ed.e_marketplace.dto.user.UserResponseDto;
import com.mar1a_ed.e_marketplace.dto.user.UserUpdatePasswordDto;
import com.mar1a_ed.e_marketplace.model.entity.User;
import com.mar1a_ed.e_marketplace.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto userCreateDto){
        User user = UserMapper.toUser(userCreateDto);
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> findById(@Valid @PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> findAll(){
        List<User> users = userService.findAll();
        List<UserResponseDto> usersDto = UserMapper.toListDto(users);
        return ResponseEntity.ok().body(usersDto);
    }

    @PatchMapping("/update-password/{id}")
    public ResponseEntity<Void> updatePassword(@Valid @PathVariable Long id, @RequestBody UserUpdatePasswordDto userUpdatePasswordDto){
        userService.updatePassword(id, userUpdatePasswordDto.getCurrentPassword(), userUpdatePasswordDto.getNewPassword(), userUpdatePasswordDto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }
}
