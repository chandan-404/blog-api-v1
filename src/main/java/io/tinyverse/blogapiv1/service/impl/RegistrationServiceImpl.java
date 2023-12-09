package io.tinyverse.blogapiv1.service.impl;

import io.tinyverse.blogapiv1.entity.User;
import io.tinyverse.blogapiv1.payload.UserDto;
import io.tinyverse.blogapiv1.repository.UserRepository;
import io.tinyverse.blogapiv1.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User userEntity = userRepository.save(user);
        return mapToDto(userEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    private User mapToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    private UserDto mapToDto(User user) {
        return mapper.map(user, UserDto.class);
    }
}
