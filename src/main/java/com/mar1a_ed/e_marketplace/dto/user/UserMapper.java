package com.mar1a_ed.e_marketplace.dto.user;

import com.mar1a_ed.e_marketplace.model.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(UserCreateDto userCreateDto){
        return new ModelMapper().map(userCreateDto, User.class);
    }

    public static UserResponseDto toDto(User user){
        String role = user.getRole().name().substring("ROLE_".length());
        PropertyMap<User, UserResponseDto> propUser = new PropertyMap<User, UserResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propUser);
        return modelMapper.map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<User> users){
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
