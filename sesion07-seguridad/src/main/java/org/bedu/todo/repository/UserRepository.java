package org.bedu.todo.repository;

import java.util.Optional;

import org.bedu.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findOneByUsername(String username);
}
