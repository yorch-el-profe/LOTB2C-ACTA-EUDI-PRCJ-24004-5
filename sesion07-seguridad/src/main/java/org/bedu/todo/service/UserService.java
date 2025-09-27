package org.bedu.todo.service;

import org.bedu.todo.entity.User;
import org.bedu.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public User save(User user) {
        // Antes de guardar la contraseña, la ciframos
        user.setPassword(encoder.encode(user.getPassword()));
        
        return repository.save(user);
    }
}
