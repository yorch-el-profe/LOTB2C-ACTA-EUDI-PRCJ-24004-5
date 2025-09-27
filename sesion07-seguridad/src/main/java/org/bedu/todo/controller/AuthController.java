package org.bedu.todo.controller;

import org.bedu.todo.entity.User;
import org.bedu.todo.service.JwtService;
import org.bedu.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        return jwtService.generateToken(user.getUsername());
    }
}
