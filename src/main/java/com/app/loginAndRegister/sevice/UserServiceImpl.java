package com.app.loginAndRegister.sevice;

import com.app.loginAndRegister.converter.UserConverter;
import com.app.loginAndRegister.dto.UserDto;
import com.app.loginAndRegister.entity.User;
import com.app.loginAndRegister.exception.SpringException;
import com.app.loginAndRegister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private final String ERROR_1 = "User not found";

    @Autowired
    private UserConverter userConverter;

    public UUID generateId() {
        UUID id;
        do{
            id = UUID.randomUUID();
        }while(userRepository.existsById(id));
        return id;
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) throws SpringException {

        UUID id = generateId();
        userDto.setId(id);

        User user = userConverter.convertToEntity(userDto);
        userRepository.save(user);

        return userConverter.convertToDto(user);

    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) throws SpringException {

        User user = userRepository.findByIdAndDateOffIsNull(userDto.getId());

        if(user == null){
            throw new SpringException(ERROR_1);
        }

        user = userConverter.convertToEntity(userDto);
        userRepository.save(user);

        return userConverter.convertToDto(user);

    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findById(UUID id) throws SpringException {

        User user = userRepository.findByIdAndDateOffIsNull(id);

        if(user == null){
            throw new SpringException(ERROR_1);
        }
        return  userConverter.convertToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByUsername(String username) throws SpringException {

        User user = userRepository.findByUsernameAndDateOffIsNull(username);

        if(user == null){
            throw new SpringException(ERROR_1);
        }
        return userConverter.convertToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<UserDto> findAll() throws SpringException {

        Set<UserDto> usersDtos = new HashSet<>();

        for(User user : userRepository.findAllByDateOffIsNull()){
            usersDtos.add(userConverter.convertToDto(user));
        }
        if(usersDtos.isEmpty()){
            throw new SpringException("The user list is empty");
        }
        return usersDtos;
    }

    @Override
    @Transactional
    public void delete(UUID id) throws SpringException {

        User user = userRepository.findByIdAndDateOffIsNull(id);

        if(user == null){
            throw new SpringException("There is not user in database");
        }
        user.setDateOff(new Date());
        userRepository.save(user);

    }

}
