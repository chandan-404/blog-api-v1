package io.tinyverse.blogapiv1.service;

import io.tinyverse.blogapiv1.payload.UserDto;

public interface RegistrationService {
    public UserDto addUser(UserDto userDto);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
