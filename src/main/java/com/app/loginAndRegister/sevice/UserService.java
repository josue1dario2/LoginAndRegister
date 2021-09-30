package com.app.loginAndRegister.sevice;

import com.app.loginAndRegister.dto.UserDto;
import com.app.loginAndRegister.exception.SpringException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.UUID;

public interface UserService {

    UUID generateId();

    UserDto create(UserDto userDto) throws SpringException;

    UserDto update(UserDto userDto) throws SpringException;

    UserDto findById(UUID id) throws SpringException;

    UserDto findByUsername(String username) throws SpringException;

    Set<UserDto> findAll() throws SpringException;

    void delete(UUID id) throws SpringException;


}
