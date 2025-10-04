package org.bedu.todo.controller;

import javax.naming.AuthenticationException;

import org.bedu.todo.entity.User;
import org.bedu.todo.service.JwtService;
import org.bedu.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return service.save(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) throws AuthenticationException {
        // 1. Valida la existencia del usuario en BD
        // 2. Valida la contraseña
        // 3. Verifica que sea la contraseña
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        return jwtService.generateToken(user.getUsername());
    }
}
