package com.app.loginAndRegister.converter;

import com.app.loginAndRegister.dto.UserDto;
import com.app.loginAndRegister.entity.User;

public class UserConverter {

    public User convertToEntity(UserDto userDto){

        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRol(userDto.getRol());

        return user;
    }
    public UserDto convertToDto(User user){

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRol(user.getRol());

        return userDto;
    }
}
