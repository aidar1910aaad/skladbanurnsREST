package com.banurns.skladbanurnsrest.security;

import com.banurns.skladbanurnsrest.model.User;
import com.banurns.skladbanurnsrest.security.jwt.JwtUser;
import com.banurns.skladbanurnsrest.security.jwt.JwtUserFactory;
import com.banurns.skladbanurnsrest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService UserService;

    @Autowired
    public JwtUserDetailsService(UserService UserService){
        this.UserService = UserService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserService.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
