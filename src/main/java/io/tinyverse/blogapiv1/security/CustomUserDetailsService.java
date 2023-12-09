package io.tinyverse.blogapiv1.security;

import io.tinyverse.blogapiv1.config.UserInfoDetails;
import io.tinyverse.blogapiv1.entity.User;
import io.tinyverse.blogapiv1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> byUsername = userRepository.findByUsername(username);

        return byUsername.map(UserInfoDetails::new)
                .orElseThrow(
                        ()-> new UsernameNotFoundException("Username not found! s" + username)
                );
    }
}
