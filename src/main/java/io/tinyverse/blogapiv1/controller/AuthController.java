package io.tinyverse.blogapiv1.controller;

import io.tinyverse.blogapiv1.entity.Role;
import io.tinyverse.blogapiv1.entity.User;
import io.tinyverse.blogapiv1.payload.LoginDto;
import io.tinyverse.blogapiv1.payload.UserDto;
import io.tinyverse.blogapiv1.repository.RoleRepository;
import io.tinyverse.blogapiv1.repository.UserRepository;
import io.tinyverse.blogapiv1.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User sign-in Successfully!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){

        if (registrationService.existsByUsername(userDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (registrationService.existsByEmail(userDto.getEmail())){
            return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
        }

//        User user = new User();
//        user.setName(userDto.getName());
//        user.setUsername(userDto.getUsername());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
//        user.setRoles(userDto.getRoles());

        registrationService.addUser(userDto);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
