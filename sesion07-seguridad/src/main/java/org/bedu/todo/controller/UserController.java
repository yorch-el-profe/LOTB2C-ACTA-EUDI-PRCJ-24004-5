package org.bedu.todo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserController {
    
    @GetMapping("me")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }
}
